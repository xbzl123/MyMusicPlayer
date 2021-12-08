package com.example.mymusicplayer.utils

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.*
import java.lang.Exception

fun <T> xml2Java(file:String){
    try {

        val factory = XmlPullParserFactory.newInstance()

        val newPullParser = factory.newPullParser()

        newPullParser.setInput(StringReader(file))

        var eventType = newPullParser.eventType

        var cityName = ""

        var weather = ""

        while (eventType != XmlPullParser.END_DOCUMENT){
            when(eventType){
                XmlPullParser.START_TAG->
                    when(newPullParser.name){
                        "city"->cityName = newPullParser.nextText()
                    }
                XmlPullParser.END_TAG->
                    if(newPullParser.name == "aomen"){
                        Log.e("tag",""+cityName)
                    }
            }
            eventType = newPullParser.next()
        }

    }catch (e:Exception){
        Log.e("tag","解析错误 ${e}")
    }
}

fun readFile(file:File) {
    var fileInputStream:FileInputStream = FileInputStream(file)
    var br:BufferedReader = BufferedReader(InputStreamReader(fileInputStream,"utf-8"))
}
class DocumentUtils {

}