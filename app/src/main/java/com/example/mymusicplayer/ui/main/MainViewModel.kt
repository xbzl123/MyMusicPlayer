package com.example.mymusicplayer.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.wifi.WifiManager
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Log
import androidx.databinding.ObservableField
import com.blankj.utilcode.util.DeviceUtils
import com.blankj.utilcode.util.Utils
import com.bugrui.buslib.LiveDataBus
import com.example.mymusicplayer.http.WeatherApi
import com.example.mymusicplayer.http.WeatherCity
import com.example.mymusicplayer.utils.CountryCodeToRegionCodeUtil
import com.example.mymusicplayer.utils.HanziToPinyin
import com.example.mymusicplayer.viewmodel.LifecycleViewModel
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*
import okhttp3.ResponseBody
import java.io.IOException
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import kotlin.Comparator
import kotlin.collections.ArrayList


data class WeatherInfo(var address:LocaleInfo,var tempe:String,var weather:String)

data class LocaleInfo(val country:String,val province:String,val city:String)

@SuppressLint("MissingPermission")
class MainViewModel : LifecycleViewModel() {
    var address:ObservableField<String> = ObservableField("no locale")

    init {
        val locationManager = Utils.getApp().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,2000L,10f,object :LocationListener{
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

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }

            override fun onProviderEnabled(provider: String) {
            }

            override fun onProviderDisabled(provider: String) {
            }
        },null)

        getCountriesList()
    }

    private fun getCountriesList() {
        val isoCountries = Locale.getISOCountries()
        val arrayList = ArrayList<Locale>()

        isoCountries.map {
            arrayList.add(Locale("",it))
        }
        arrayList.map {
            val instance = PhoneNumberUtil.getInstance()
//            val formatNumberToE164 = PhoneNumberUtils.formatNumberToE164("4152620441", "US")
//            Log.e("getCountriesList","isO3Country:"+it.isO3Country+",formatNumberToE164 :"+ CountryCodeToRegionCodeUtil.getPhonePrefixByCountry(it.country) +", name ="+it.displayName+",code = "+it.country)
            val phonePrefixCode =
                CountryCodeToRegionCodeUtil.getPhonePrefixByCountry(it.country)
            CountryPhones(it.country,it.displayName,phonePrefixCode)
        }.filter {
            !"".equals(it.phoneNumber)
        }.apply {
            this.sortedWith(object :Comparator<CountryPhones> {
                override fun compare(o1: CountryPhones?, o2: CountryPhones?): Int {
                    if (o2?.enName.equals("#")) {
                        return -1;
                    } else if (o1?.enName.equals("#")) {
                        return 1;
                    } else {
                        if (o2 != null || o1 !== null) {
                            return o1!!.enName.compareTo(o2!!.enName)
                        }
                    }
                    return 0
                }
            }).map {
                Log.e("getCountriesList","name ="+it.country+",enName ="+it.enName+",code = "+it.phoneNumber)
            }
        }
        val toString = UUID.randomUUID().toString()
        val wifiManager = Utils.getApp().getApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
//        Log.e("getCountriesList","toString ="+ DeviceUtils.getMacAddress());
//        try {
//            val systemProperties = Class.forName("android.os.SystemProperties")
//            val get: Method = systemProperties.getMethod("get", String::class.java)
//            // get homeOperator that contain MCC + MNC
//            val homeOperator = get.invoke(
//                systemProperties,
//                "gsm.current.phone-type"
//            ) as String
//
//            // first 3 chars (MCC) from homeOperator represents the country code
//            val mcc = homeOperator
//        }catch (e:Exception){
//            Log.e("Exception","it :"+e.message)
//        }
    }

    private fun requstWeather(city: String, subLocality: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://flash.weather.com.cn/wmaps/xml/")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        val service: WeatherApi = retrofit.create(WeatherApi::class.java)

        val observable: io.reactivex.Observable<ResponseBody> = service.getCtiyWeather1(city)
        observable.subscribeOn(Schedulers.io())
            .subscribe(Consumer {
            val result = it.string()?.trimIndent()
            try {
                result?.replace(city,"weather_city")
                //xml格式转成实体类
//                val serializer: Serializer = Persister()
//                val city = serializer.read(WeatherCity::class.java, result)
//                Log.e("tag","解析的结果： "+city.lists.first().centername)
//                refreshCityWeather(city,subLocality)
            } catch (e: IOException) {
                e.printStackTrace()
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
//        Toast.makeText(Utils.getApp(),"test", Toast.LENGTH_LONG).show()
    }

}

data class CountryPhones(val country: String,val enName: String, val phoneNumber:String)