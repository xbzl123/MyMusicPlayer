package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class Clock @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var size: Int = 0
    val padding = 15f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = MeasureSpec.getSize(widthMeasureSpec)
    }
    var progress:Int = 0
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paintBg = Paint()
        paintBg.color = Color.BLACK
        paintBg.strokeWidth = (size/400).toFloat()
        paintBg.style = Paint.Style.STROKE
        canvas?.drawCircle((size/2).toFloat(),(size/2).toFloat(),(size/2).toFloat() - padding,paintBg)

        for (i in 0..60){

            canvas?.save()
            Log.e("onDraw","rotate  i = "+i)
            canvas?.rotate(6f*i,(size/2).toFloat(),(size/2).toFloat())
            var temp:Float
            if(i > 0 && i%5==0){
                temp = 20f + padding
                paintBg.textSize = (size/25).toFloat()
                canvas?.drawText((i/5).toString(),(size/2 - size/50).toFloat(),(size/15).toFloat()+padding,paintBg)
            }else{
                temp = 10f + padding
            }
            canvas?.drawLine((size/2).toFloat(),padding, (size/2).toFloat(), temp,paintBg)
            canvas?.restore()
        }


        var paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 10f

        canvas?.save()
        canvas?.rotate(6f*progress,(size/2).toFloat(),(size/2).toFloat())
        canvas?.drawLine((size/2).toFloat(),(size*0.15).toFloat(), (size/2).toFloat(), (size/2).toFloat(),paint)
        canvas?.restore()

        var paintM = Paint()
        paintM.color = Color.RED
        paintM.strokeWidth = 5f
        canvas?.save()
        canvas?.drawCircle((size/2).toFloat(),(size/2).toFloat(),10f,paintM)
        canvas?.rotate(6f*progress/60,(size/2).toFloat(),(size/2).toFloat())
        canvas?.drawLine((size/2).toFloat(),(size*0.25).toFloat(), (size/2).toFloat(), (size/2).toFloat(),paintM)
        canvas?.restore()

        val min = progress / 60
        val sec = progress % 60
        var minValue = ""
        var secValue = ""

        minValue = if (min < 10){
            "0$min"
        }else {
            min.toString()
        }
        secValue = if (sec < 10){
            "0$sec"
        }else {
            sec.toString()
        }
        val timeValue = "$minValue:$secValue"
        canvas?.drawText(timeValue,(size/2.2).toFloat(),(size + size/15).toFloat(),paintBg)

    }
    fun startAnimationRatotion() {
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io()).subscribe {
                progress++
                invalidate()
            }
    }
}