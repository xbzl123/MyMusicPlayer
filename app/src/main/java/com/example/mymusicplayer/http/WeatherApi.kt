package com.example.mymusicplayer.http

import io.reactivex.Observable
import okhttp3.ResponseBody
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import retrofit2.Call
import retrofit2.http.HTTP
import retrofit2.http.Path
//参考网站：https://medium.com/codechai/how-to-parse-xml-using-kotlin-in-2021-21f2fffbdf22

@Root(strict = false, name = "city")
class City {
    @field:Attribute(name = "cityname", required = false)
    var cityname: String? = null

    @field:Attribute(name = "centername", required = false)
    var centername: String? = null

    @field:Attribute(name = "fontColor", required = false)
    var fontColor: String? = null

    @field:Attribute(name = "cityX", required = false)
    var cityX: String? = null

    @field:Attribute(name = "cityY", required = false)
    var cityY: String? = null

    @field:Attribute(name = "pyName", required = false)
    var pyName: String? = null

    @field:Attribute(name = "state1", required = false)
    var state1: String? = null

    @field:Attribute(name = "state2", required = false)
    var state2: String? = null

    @field:Attribute(name = "tem1", required = false)
    var tem1: String? = null

    @field:Attribute(name = "tem2", required = false)
    var tem2: String? = null

    @field:Attribute(name = "temNow", required = false)
    var temNow: String? = null

    @field:Attribute(name = "windState", required = false)
    var windState: String? = null

    @field:Attribute(name = "windDir", required = false)
    var windDir: String? = null

    @field:Attribute(name = "windPower", required = false)
    var windPower: String? = null

    @field:Attribute(name = "stateDetailed", required = false)
    var stateDetailed: String? = null

    @field:Attribute(name = "humidity", required = false)
    var humidity: String? = null

    @field:Attribute(name = "time", required = false)
    var time: String? = null

    @field:Attribute(name = "url", required = false)
    var url: String? = null

}

@Root(strict = false, name = "weather_city")
class WeatherCity {
    @field:Attribute(name = "dn", required = false)
    var dn: String? = null

    @field:ElementList(entry = "city", required = false,inline = true)
    lateinit var lists: List<City>
}

interface WeatherApi {
    @HTTP(method = "GET", path = "{city}.xml", hasBody = false)
    fun getCtiyWeather(@Path("city") city:String):Call<ResponseBody>

    @HTTP(method = "GET", path = "{city}.xml", hasBody = false)
    fun getCtiyWeather1(@Path("city") city:String): Observable<ResponseBody>
}


