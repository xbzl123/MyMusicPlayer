package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.databinding.Observable
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

class RadarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val startAngle = 50f
    private var widthReal: Float = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthReal = MeasureSpec.getSize(widthMeasureSpec).toFloat()
    }

    var state = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.let {
            var paint = Paint()
            paint.color = Color.RED
            var rect = RectF()
            rect.set(widthReal/4,widthReal/4,widthReal*0.75f,widthReal*0.75f)
            paint.strokeWidth = 20f
            paint.style = Paint.Style.STROKE
            it?.drawCircle(widthReal/2,widthReal,widthReal/4*(0.1f*state),paint)

/*            //绘制透明部分
            paint.setARGB(30, 127, 255, 212);
            it?.drawArc(rect, 90+startAngle, 90f, false, paint);
            it?.drawArc(rect, 270+startAngle, 90f, false, paint);*/
        }
    }

    fun startAnimationExpand(){
        io.reactivex.Observable.interval(0,500,TimeUnit.MILLISECONDS).subscribe(
            {
                if(it > 10) state =(it%10).toInt() else state = it.toInt()
                invalidate()
            }
        )
    }
}