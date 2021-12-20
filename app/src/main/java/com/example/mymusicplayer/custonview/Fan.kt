package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class Fan @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var size: Int = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = MeasureSpec.getSize(widthMeasureSpec)
    }
    var progress:Int = 0
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        var paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 10f
        canvas?.drawArc(0f,0f, (size/2).toFloat(), (size/2).toFloat(),0f,progress.toFloat(),true,paint)
    }
    fun startAnimationRatotion() {
        Observable.interval(0, 100, TimeUnit.MILLISECONDS).subscribe(
            {
                if (progress > 300) return@subscribe
                progress = (it*3.6f).toInt()
                invalidate()
            }
        )
    }
}