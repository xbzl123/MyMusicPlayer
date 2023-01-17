package com.example.mymusicplayer.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RadarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var widthReal: Float = 0f
    private var speed: Long = 1
    var startAngle = 0f
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthReal = MeasureSpec.getSize(widthMeasureSpec).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.let {
            var paint = Paint()
            paint.color = Color.RED
            var rect = RectF()
            rect.set(widthReal/4,widthReal/4,widthReal*0.75f,widthReal*0.75f)
            paint.strokeWidth = 3f
            paint.style = Paint.Style.STROKE
            it?.save()
            for (i in 0..10) {
                it?.drawCircle(widthReal / 2, widthReal, widthReal / 4 * (0.1f * i), paint)
            }
            it?.restore()

            //渐进背景色
            var paintBg = Paint()
            var lg=LinearGradient(widthReal/2,widthReal/2,widthReal/2,widthReal,Color.GREEN,Color.TRANSPARENT,Shader.TileMode.MIRROR)
            paintBg.setShader(lg)

            it?.rotate(startAngle,widthReal / 2, widthReal)
            it?.drawArc(widthReal*0.25f, widthReal*0.75f,widthReal*0.75f,widthReal*1.25f,0f,60f,true,paintBg)

        }
    }

    fun startAnimationScan(){
        io.reactivex.Observable.interval(0,50/speed,TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io()).subscribe(
            {
                startAngle = it*3.6f
                invalidate()
            }
        )
    }

    fun setSpeedx2(){
        this.speed = speed * 2
    }
}