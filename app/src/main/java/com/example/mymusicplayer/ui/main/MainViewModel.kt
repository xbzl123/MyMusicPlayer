package com.example.mymusicplayer.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.Utils
import com.example.mymusicplayer.http.WeatherApi
import com.example.mymusicplayer.utils.HanziToPinyin
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import java.util.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import retrofit2.Retrofit





data class WeatherInfo(var address:LocaleInfo,var tempe:String,var weather:String)

data class LocaleInfo(val country:String,val province:String,val city:String)

@SuppressLint("MissingPermission")
class MainViewModel : LifecycleViewModel() {
    var address:ObservableField<String> = ObservableField("no locale")

    init {
        val locationManager = Utils.getApp().getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        val lastKnownLocation: Location? = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000L,10f,object :LocationListener{
            override fun onLocationChanged(location: Location) {
                val geocoder = Geocoder(Utils.getApp(), Locale.getDefault())
                val fromLocation = geocoder.getFromLocation(location.latitude, location.longitude,10)
                val lastLocation = fromLocation.get(0)
                val addr = lastLocation.countryName + lastLocation.adminArea + lastLocation.locality + lastLocation.subLocality
                address.set(addr)
                val cityName = lastLocation.locality
                val substring = cityName.substring(0, cityName.length - 1)
                val instance = HanziToPinyin.getInstance()
                val get = instance?.get(substring)
                var result = StringBuilder()

                result.let {
                    for (i in 0..get!!.size-1){
                        result.append(get[i].target)
                    }
                }

                var toString:String = result.toString().toLowerCase()
                requstWeather(toString)
               // "http://flash.weather.com.cn/wmaps/xml/aomen.xml"
                Toast.makeText(Utils.getApp(),addr, Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun requstWeather(city: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://flash.weather.com.cn/wmaps/xml/")
            .build()
        val service: WeatherApi = retrofit.create(WeatherApi::class.java)

        val call: Call<ResponseBody> = service.getCtiyWeather(city)
        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                try {
                    Toast.makeText(Utils.getApp(),response.body()?.string(), Toast.LENGTH_LONG).show()
                    System.out.println(response.body()?.string())
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun test(){
        Toast.makeText(Utils.getApp(),"test", Toast.LENGTH_LONG).show()
    }

}