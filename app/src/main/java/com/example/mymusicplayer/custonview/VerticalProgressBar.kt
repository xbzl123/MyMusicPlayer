package com.example.mymusicplayer.custonview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class VerticalProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var NUM: Int = 0
    val interval = 50f
    val heigtLength = 200L
    val expandWith = 30L

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }
    var size_height = 0F;
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size_with = MeasureSpec.getSize(widthMeasureSpec)
        size_height = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        NUM = (size_height/interval).toInt()
        Log.e("test","size_with = "+size_with+",size_height = "+size_height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = Color.GREEN
        val rect = RectF()

        rect.set(5F,0F,expandWith+10F,size_height)
        canvas?.drawRoundRect(rect,10F,10F,paint)
        var paintLine = Paint()
        paintLine.color = Color.BLACK

        var paintText = Paint()
        paintText.textSize = 10f
        paintText.color = Color.BLUE
        paintText.textAlign = Paint.Align.CENTER
        for (i in 1..NUM-1){
            canvas?.let {
                it.drawLine(expandWith + 10f,interval * i,expandWith + 15f,interval * i,paintLine)
                it.drawText(i.toString(),expandWith + 40f,interval * i+3,paintText)
            }
        }
//        canvas?.save()
//        canvas?.translate(0f,200f)
    }

    var Y1 = 0f
    var Y2 = 0f
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var action = event?.action
        Log.e("test","event = "+action+",y"+event?.y)
        when(action ){
            MotionEvent.ACTION_DOWN->{
            }
            MotionEvent.ACTION_MOVE->{
                Y1 = event?.y!!
                if(Y1 != 0f){
                    this.translationY = Y1
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP->{
                Y1 = 0f
            }

        }
        return true
    }
}