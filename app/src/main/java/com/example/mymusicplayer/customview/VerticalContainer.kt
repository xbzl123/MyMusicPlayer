package com.example.mymusicplayer.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

class VerticalContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    var beforeY: Float = 0f
    var heightSize: Float = 0f
    var moveduration = 0.0f
    var verticalProgressBar:VerticalProgressBar
    init {
        this.setBackgroundColor(Color.parseColor("#ffffff"))
        verticalProgressBar = VerticalProgressBar(context)
        verticalProgressBar.setMoveListener(object :VerticalProgressBar.MoveResult{
            override fun onMove(duration: Float) {
                moveduration = duration
            }
        })
        this.addView(verticalProgressBar)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        heightSize = MeasureSpec.getSize(heightMeasureSpec).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color = Color.RED
        canvas?.let {
            it.drawLine(40f,heightSize/2,100f,heightSize/2,paint)
            paint.textSize = 40f
            it.drawText(String.format("%.1f",(verticalProgressBar.NUM/2 - moveduration)),110f,heightSize/2,paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action
        when(action){
            MotionEvent.ACTION_DOWN->{
                beforeY = event?.y!!
            }
            MotionEvent.ACTION_MOVE->{
                var offsetY = y?.minus(beforeY).toInt()
                verticalProgressBar.scrollTo(0,offsetY)
            }
        }
        return true
    }
}