package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.concurrent.TimeUnit

class WifiScan @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var step= 0
    private var size: Int = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = MeasureSpec.getSize(widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.let {
            var paint = Paint()
            paint.color = Color.BLACK
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 30f

            //画贝赛尔曲线
            var path = Path()
            for (i in 0..4){
                if(step == i){
                    continue
                }
                //设置起点
                path.moveTo(0f+(i*100f),(size/2).toFloat()+(i*100f))
                //设置控制点和终点
                path.quadTo((size/2).toFloat(),0f+(i*200f),size.toFloat()-(i*100f),(size/2).toFloat()+(i*100f))
                it?.drawPath(path,paint)
            }
            paint.style = Paint.Style.FILL
            it?.drawCircle((size/2).toFloat(),size.toFloat(),50f,paint)
        }
    }

    fun startAnimationScan(){
        io.reactivex.Observable.interval(0,500, TimeUnit.MILLISECONDS).subscribe(
            {
                step = (it % 5).toInt()
                invalidate()
            }
        )
    }
}