package com.example.mymusicplayer.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
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
            paint.strokeWidth = (size/30).toFloat()

            //画贝赛尔曲线
            var path = Path()
            if (step != -1){
                for (i in 0..4){
                    if(step == i){
                        continue
                    }
                    //设置起点
                    path.moveTo(0f+(i*(size/10).toFloat()),(size/2).toFloat()+(i*(size/10).toFloat()))
                    //设置控制点和终点
                    path.quadTo((size/2).toFloat(),0f+(i*(size/5).toFloat()),size.toFloat()-(i*(size/10).toFloat()),(size/2).toFloat()+(i*(size/10).toFloat()))
                    it?.drawPath(path,paint)
                }
            }
            paint.style = Paint.Style.FILL
            it?.drawCircle((size/2).toFloat(),size.toFloat(),(size/20).toFloat(),paint)
        }
    }

    lateinit var disposable:Disposable
    fun startAnimationScan(){
        disposable = io.reactivex.Observable.interval(0, 500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io()).subscribe {
            step = (it % 5).toInt()
            invalidate()
        }
    }

    fun stopAnimationScan(isSuccess:Boolean){
        if(isSuccess){
            step = 5
        } else{
            step = -1
        }
        invalidate()
        disposable.dispose()
    }
}