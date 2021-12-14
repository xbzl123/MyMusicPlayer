package com.example.mymusicplayer.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.Utils
import com.bugrui.buslib.LiveDataBus
import com.example.mymusicplayer.R
import com.example.mymusicplayer.http.WeatherApi
import com.example.mymusicplayer.http.WeatherCity
import com.example.mymusicplayer.utils.HanziToPinyin
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import java.util.*
import okhttp3.ResponseBody
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000L,10f,object :LocationListener{
            override fun onLocationChanged(location: Location) {
                val geocoder = Geocoder(Utils.getApp(), Locale.getDefault())
                //坐标转成位置类
                val fromLocation = geocoder.getFromLocation(location.latitude, location.longitude,10)
                val lastLocation = fromLocation.get(0)
                //拼接成详细的地址
                val addr = lastLocation.countryName + lastLocation.adminArea + lastLocation.locality + lastLocation.subLocality
                //值绑定给观察者
                address.set(addr)
                val cityName = lastLocation.locality
                val subCity = cityName.substring(0, cityName.length - 1)
                //汉字字符串转拼音字符串
                val instance = HanziToPinyin.getInstance()
                val getPinyin = instance?.get(subCity)
                var result = StringBuilder()
                //拼接拼音字符串
                result?.let {
                    for (i in 0..getPinyin!!.size-1){
                        result.append(getPinyin[i].target)
                    }
                }

                var pinyinName:String = result.toString().toLowerCase()
                //网络请求目标城市的天气信息
                requstWeather(pinyinName,lastLocation.subLocality)
            }
        })
    }

    private fun requstWeather(city: String, subLocality: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://flash.weather.com.cn/wmaps/xml/")
            .build()
        val service: WeatherApi = retrofit.create(WeatherApi::class.java)

        val call: Call<ResponseBody> = service.getCtiyWeather(city)
        // 用法和OkHttp的call如出一辙,
        // 不同的是如果是Android系统回调方法执行在主线程
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                val result = response.body()?.string()?.trimIndent()
                try {
//                    Toast.makeText(Utils.getApp(),result, Toast.LENGTH_LONG).show()
                    result?.replace(city,"weather_city")
                    //xml格式转成实体类
                    val serializer: Serializer = Persister()
                    val city = serializer.read(WeatherCity::class.java, result)
                    Log.e("tag","解析的结果： "+city.lists.first().centername)
                    refreshCityWeather(city,subLocality)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    private fun refreshCityWeather(city: WeatherCity, subLocality: String) {
        val subCity = subLocality?.substring(0, subLocality.length - 1)
        val lists = city.lists
        var nameList = ""
        if(lists.size > 1){
            lists.forEach {
                nameList+=it.cityname
            }.apply {
                //存在区或者镇名符合的天气搜索结果
                if(nameList.contains(subCity)){
                    lists.forEach {
                        if(it.centername?.contains(subCity) == true){
                            address.set(address.get()+"\n气温是:"+it.tem2+"`C~"+it.tem1+"`C"
                                    +"，现在的室外温度:"+it.temNow+"，天气是:"+it.stateDetailed+",湿度:"+it.humidity)
                        }
                    }
                }else{
                    //不符合结果就直接使用市的天气
                    lists.forEach {
                        if(it.centername?.contains("市") == true){
                            address.set(address.get()+"\n气温是:"+it.tem2+"`C~"+it.tem1+"`C"
                                    +"，现室外温度:"+it.temNow+"，天气是:"+it.stateDetailed+",湿度:"+it.humidity)
                            return
                        }
                    }
                }
            }
        }else if(lists.size == 1){
            address.set(address.get()+"\n气温是:"+city.lists[0].tem2+"`C~"+city.lists[0].tem1+"`C"
                    +"，现室外温度:"+city.lists[0].temNow+"，天气是:"+city.lists[0].stateDetailed+",湿度:"+city.lists[0].humidity)
        }
    }

    fun test(){
        LiveDataBus.send("changeFragment","music")
        Toast.makeText(Utils.getApp(),"test", Toast.LENGTH_LONG).show()
    }

}