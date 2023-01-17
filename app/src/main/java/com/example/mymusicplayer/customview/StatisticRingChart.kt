package com.example.mymusicplayer.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class StatisticRingChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var region: Region
    var changeValue1 = 0f
    var colorLast = ""
    lateinit var dispose: Disposable
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
                    ClipBlockList.add(ClipBlock(colors[i],colorName[i],startAngle,startAngle+endAngle))
                }
                startAngle += endAngle
                if(isRotateOver){
                    changeValue1 = totalRotate % 360
                    if("" == colorLast){
                        var it:ClipBlock = ClipBlockList[i]
                        var startAngleLast = (it.startAngle + changeValue1)%360
                        var endAngleLast = (it.endAngle + changeValue1)%360
                                if(startAngleLast<90 && endAngleLast>90){
                                        colorLast = it.colorName
                                }
                            }
                    }
            }

            it?.restore()


            //画箭头指针
            val path = Path()
            path.moveTo(widthSize.toFloat()/2-10,widthSize.toFloat()+20)
            path.quadTo(widthSize.toFloat()/2,widthSize.toFloat()-20,widthSize.toFloat()/2+10,widthSize.toFloat()+20)

            var paint = Paint()
            paint.color = Color.RED
            paint.strokeWidth = 10f
            paint.style = Paint.Style.STROKE
            it?.drawPath(path,paint)
            it?.drawLine(widthSize.toFloat()/2,widthSize.toFloat(),widthSize.toFloat()/2,widthSize.toFloat()+50,paint)

            //写出转动的结果
            if("" != colorLast){
                val paintText = Paint()
                paintText.color = Color.BLACK
                paintText.strokeWidth = 5f
                paintText.textSize = 50f
                it?.drawText(colorLast,widthSize.toFloat()/2-25,widthSize.toFloat()+100,paintText)
                isRotate = false
            }
        }
    }

    fun insertData(data: IntArray) {
        datas = data
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action

        region = Region()
        val path = Path()
        path.addCircle(widthSize.toFloat()/2,widthSize.toFloat()/2,widthSize.toFloat()/2,Path.Direction.CW)
        region.setPath(path,Region(0,0,widthSize,widthSize))

        Log.e("tag","region ： "+region.contains(event!!.x.toInt(),event!!.y.toInt()))
        if(!region.contains(event!!.x.toInt(),event!!.y.toInt())){
            return false
        }
        if(isRotate){
            return false
        }

        when(action){
            MotionEvent.ACTION_DOWN->{
                colorLast = ""
                isRotateOver = false

                //获得千次以内的随机数。作为转动次数
                val d = 1000 * Math.random().toFloat()
                isRotate = true
                var stopValue = 1.00f
                Observable.interval(0, 20, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io()).doOnSubscribe {
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
                    },{
                        if(dispose?.isDisposed){
                            Log.e("tag","Throwable ： "+it.message)

                        }
                    }
                )
            }
        }
        return true
    }

    var ClipBlockList = arrayListOf<ClipBlock>()
}

data class ClipBlock(val color: Int,val colorName:String,var startAngle:Float,var endAngle:Float)