package com.example.mymusicplayer.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RadarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var widthReal: Float = 0f
    private var speed: Float = 1.0f
    var startAngle = 0f
    
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthReal = MeasureSpec.getSize(widthMeasureSpec).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.let {
            //画背景圆圈9个，使用红色
            var paint = Paint()
            paint.color = Color.RED
            paint.strokeWidth = 3f
            paint.style = Paint.Style.STROKE
            it?.save()
            for (i in 0..10) {
                it?.drawCircle(widthReal / 2, widthReal, widthReal / 4 * (0.1f * i), paint)
            }
            it?.restore()

            //绘制扫描扇形，使用渐进背景色，绿色到透明，旋转它使用焦点旋转的方式
            var paintBg = Paint()
            var lg=LinearGradient(widthReal/2,widthReal/2,widthReal/2,widthReal,Color.GREEN,Color.TRANSPARENT,Shader.TileMode.MIRROR)
            paintBg.setShader(lg)

            it?.rotate(startAngle,widthReal / 2, widthReal)
            it?.drawArc(widthReal*0.25f, widthReal*0.75f,widthReal*0.75f,widthReal*1.25f,0f,60f,true,paintBg)

        }
    }


    private var disposable : Disposable? = null
    //动画开始或者停止
    fun startOrStopAnimationScan(isStart:Boolean) {
        if (isStart){
            disposable  = Observable.interval(0, (50/speed).toLong(), TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io()).subscribe {
                    startAngle += 3.6f
                    invalidate()
                }
        }else{
            disposable?.dispose()
            disposable = null
        }
    }
    //旋转速度加倍
    fun setSpeedAdd(){
        this.speed = speed * 2
        startOrStopAnimationScan(false)
        startOrStopAnimationScan(true)
    }
    //旋转速度减倍
    fun setSpeedReduce(){
        this.speed = speed / 2
        startOrStopAnimationScan(false)
        startOrStopAnimationScan(true)
    }
}