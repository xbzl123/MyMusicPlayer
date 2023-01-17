package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
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
        //采用黑色作为边框（背景），画一个黑色的圆形，里面掏空，变成圆圈
        var paintBg = Paint()
        paintBg.color = Color.BLACK
        paintBg.strokeWidth = (size/400).toFloat()
        paintBg.style = Paint.Style.STROKE
        canvas?.drawCircle((size/2).toFloat(),(size/2).toFloat(),(size/2).toFloat() - padding,paintBg)

        for (i in 0..60){
            //这里保存一下之前的圆圈，开始绘制时间刻度，360度/60
            canvas?.save()
            Log.e("onDraw","rotate  i = "+i)
            canvas?.rotate(6f*i,(size/2).toFloat(),(size/2).toFloat())
            var temp:Float
            //划线：区分数字线和非数字线的长度，数据线上加上阿拉伯数字，绘制好后存储做模板
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

        //绘制秒针，蓝色填充，存储
        var paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 10f

        canvas?.save()
        canvas?.rotate(6f*progress,(size/2).toFloat(),(size/2).toFloat())
        canvas?.drawLine((size/2).toFloat(),(size*0.15).toFloat(), (size/2).toFloat(), (size/2).toFloat(),paint)
        canvas?.restore()

        //绘制分针，红色填充，存储
        var paintM = Paint()
        paintM.color = Color.RED
        paintM.strokeWidth = 5f
        canvas?.save()
        canvas?.drawCircle((size/2).toFloat(),(size/2).toFloat(),10f,paintM)
        canvas?.rotate(6f*progress/60,(size/2).toFloat(),(size/2).toFloat())
        canvas?.drawLine((size/2).toFloat(),(size*0.25).toFloat(), (size/2).toFloat(), (size/2).toFloat(),paintM)
        canvas?.restore()

        //绘制数字显示屏
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

    private var disposable :Disposable? = null

    fun startOrStopAnimationRatotion(isStart:Boolean) {
        if (isStart){
            disposable  = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io()).subscribe {
                    progress++
                    invalidate()
                }
        }else{
            disposable?.dispose()
            disposable = null
            progress = -1
        }
    }
}