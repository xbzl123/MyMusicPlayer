package com.example.mymusicplayer.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.SizeUtils

class VerticalProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var paintText: Paint
    lateinit var paint: Paint
    val DEFAULT_HEIGHT = SizeUtils.dp2px(100f)
    var NUM: Int = 0
    var interval = 0f
    lateinit var moveResult: MoveResult
    val expandWith = 30L

    var size_height = 0F;
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val size_with = MeasureSpec.getSize(widthMeasureSpec)
        size_height = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        interval = size_height/10
        NUM = (size_height/interval).toInt()*2
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint = Paint()
        paint.color = Color.GREEN
        val rect = RectF()

        rect.set(5F,0F,expandWith+10F,size_height*2)
        canvas?.drawRoundRect(rect,10F,10F,paint)
        var paintLine = Paint()
        paintLine.color = Color.BLACK

        paintText = Paint()
        paintText.textSize = 25f
        paintText.color = Color.BLUE
        paintText.textAlign = Paint.Align.CENTER
        for (i in 0..NUM-1){
            canvas?.let {
                it.drawLine(expandWith + 10f,interval * i,expandWith + 15f,interval * i,paintLine)
                it.run {
                    for (j in 1 .. 9){
                        this.drawLine(expandWith + 10f,interval * (i +0.1f*j),expandWith + 12f,interval * (i +0.1f*j),paintLine)
                    }
                }
                it.drawText((i).toString(),expandWith + 40f,interval * i+3,paintText)
            }
        }
    }

    var beforeY = 0f
    var moveStart = 0
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var action = event?.action
        when(action){
            MotionEvent.ACTION_DOWN->{
                beforeY = event?.y!!
            }
//            MotionEvent.ACTION_MOVE->{
//                //计算偏移量
//                var offsetY = y?.minus(beforeY).toInt()
////                Log.e("onTouchEvent","onTouchEvent top = "+(top+offsetY))
//                layout(left,top+offsetY,right,bottom+offsetY)
//                Log.e("onTouchEvent","onTouchEvent top = "+(top)+",bottom = "+(bottom))
//
//                if(moveStart != top+offsetY){
//                    moveResult.onMove((top+offsetY)/(interval)*2)
//                }
//                moveStart = top+offsetY
//
//            }
            MotionEvent.ACTION_UP->{
            }
        }
        return true
    }
    fun setMoveListener(moveResult: MoveResult){
        this.moveResult = moveResult
    }

    interface MoveResult{
        fun onMove(duration:Float)
    }
}