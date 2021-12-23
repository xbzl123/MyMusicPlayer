package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class StatisticRingChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    var widthSize = 0
    var colors = intArrayOf(Color.BLACK,Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW,Color.CYAN,Color.GRAY)
    lateinit var datas:IntArray
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthSize = MeasureSpec.getSize(widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.let {
            val rectF = RectF(0f, 0f, widthSize.toFloat(), widthSize.toFloat())

            var total = 0
            datas.map { total+=it }
            var startAngle = 0f
            var endAngle = 0f

            for (i in 0..datas.size-1){
                var paint = Paint()
                paint.color = colors[i]
                endAngle = (datas[i].toFloat()*360/total.toFloat())
                it?.drawArc(rectF,startAngle,endAngle,true,paint)
                startAngle += endAngle
            }
        }
    }

    fun insertData(data: IntArray) {
        datas = data
        invalidate()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        when(action){
            MotionEvent.ACTION_MOVE->{

            }
        }
        return true
    }
}