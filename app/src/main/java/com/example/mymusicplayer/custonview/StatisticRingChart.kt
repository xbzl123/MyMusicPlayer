package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit


class StatisticRingChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var dispose: Disposable? = null
    var isRotate = false
    var isRotateOver = false
    var progress = 0f
    var degress = 3.6f
    var widthSize = 0
    var colorName = arrayOf("黑","绿","蓝","红","黄","靛","灰")
    var colors = intArrayOf(Color.BLACK,Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW,Color.CYAN,Color.GRAY)
    lateinit var datas:IntArray
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthSize = MeasureSpec.getSize(widthMeasureSpec)
    }

    var totalRotate = 0f
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawFilter
        canvas.let {
            it?.save()
            val rectF = RectF(0f, 0f, widthSize.toFloat(), widthSize.toFloat())
            var total = 0
            datas.map { total+=it }
            var startAngle = 0f
            var endAngle = 0f
            if(isRotate){
                it?.rotate(degress*progress,widthSize.toFloat()/2,widthSize.toFloat()/2)
                totalRotate = degress*progress
            }
            for (i in 0..datas.size-1){
                var paint = Paint()
                paint.color = colors[i]
                endAngle = (datas[i].toFloat()*360/total.toFloat())
                it?.drawArc(rectF,startAngle,endAngle,true,paint)
                if(ClipBlockList.size < datas.size){
                    ClipBlockList.add(ClipBlock(colors[i],startAngle,startAngle+endAngle))
                }
                startAngle += endAngle
                if(isRotateOver){
                    var changeValue = totalRotate % 360
                    Log.e("tag","changeValue: "+changeValue +",totalRotate:"+totalRotate)

                    kotlin.run outside@{
                        ClipBlockList.map {
                            it.startAngle = (it.startAngle + changeValue)%360
                            it.endAngle = (it.endAngle + changeValue)%360
                            if(it.startAngle<90 && it.endAngle>90){
                                Log.e("tag1","it: "+it.color)
                                return@outside
                            }
                        }
                    }

                }
            }

            it?.restore()
            val path = Path()
            path.moveTo(widthSize.toFloat()/2-10,widthSize.toFloat()+20)
            path.quadTo(widthSize.toFloat()/2,widthSize.toFloat()-20,widthSize.toFloat()/2+10,widthSize.toFloat()+20)

            var paint = Paint()
            paint.color = Color.RED
            paint.strokeWidth = 10f
            paint.style = Paint.Style.STROKE
            it?.drawPath(path,paint)
            it?.drawLine(widthSize.toFloat()/2,widthSize.toFloat(),widthSize.toFloat()/2,widthSize.toFloat()+50,paint)

        }
    }

    fun insertData(data: IntArray) {
        datas = data
        invalidate()
    }


    var time = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        Log.e("tag","radom： "+Math.random())

        when(action){
            MotionEvent.ACTION_DOWN->{
                isRotateOver = false
                val d = 1000 * Math.random().toFloat()
                isRotate = true
                var stopValue = 1.00f
                Observable.interval(0, 20, TimeUnit.MILLISECONDS)
                    .doOnSubscribe {
                       dispose =  it
                    }.subscribe(
                    {//转动缓慢减速
                            stopValue-= 1/d
                            progress = progress + stopValue
                            if (it > d) {
                                dispose?.dispose()
                                isRotateOver = true
                            }
                        invalidate()
                    }
                )
            }
        }
        return true
    }

    var ClipBlockList = arrayListOf<ClipBlock>()
}

class ClipBlock(val color: Int,var startAngle:Float,var endAngle:Float)