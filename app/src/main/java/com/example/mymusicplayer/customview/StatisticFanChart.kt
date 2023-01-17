package com.example.mymusicplayer.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import com.blankj.utilcode.util.Utils

class StatisticFanChartChart @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    lateinit var region: Region
    var widthSize = 0
    var colorName = arrayOf("黑","绿","蓝","红","黄","靛","灰","白")
    var colors = intArrayOf(Color.BLACK,Color.GREEN,Color.BLUE,Color.RED,Color.YELLOW,Color.CYAN,Color.GRAY,Color.WHITE)
    lateinit var datas:IntArray

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        widthSize = MeasureSpec.getSize(widthMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas.let {
            it?.save()
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
                if(ClipBlockList.size < datas.size){
                    ClipBlockList.add(ClipBlock(colors[i],colorName[i],startAngle,startAngle+endAngle))
                }
                startAngle += endAngle
            }

            it?.restore()
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

        if(!region.contains(event!!.x.toInt(),event!!.y.toInt())){
            return false
        }

        when(action){
            MotionEvent.ACTION_DOWN->{
                val angleByXY = getAngleByXY(event!!.x, event!!.y)
                ClipBlockList.map {
                    if(it.startAngle<angleByXY && it.endAngle > angleByXY){
                        Toast.makeText(Utils.getApp(),"你的选择是:"+it.colorName,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return true
    }

    private fun getAngleByXY(x: Float, y: Float) : Float {
        if(x > widthSize.toFloat()/2){
            if(y >widthSize.toFloat()/2){
                return (Math.atan(Math.abs(y-widthSize.toFloat()/2).toDouble().div(Math.abs(x-widthSize.toFloat()/2))).times(180f.div(Math.PI)).toFloat())
            }else{
                return Math.atan(Math.abs(x-widthSize.toFloat()/2).toDouble().div(Math.abs(y-widthSize.toFloat()/2))).times(180f.div(Math.PI)).toFloat()+270f
            }
        }else{
            if(y >widthSize.toFloat()/2){
                return Math.atan(Math.abs(x-widthSize.toFloat()/2).toDouble().div(Math.abs(y-widthSize.toFloat()/2))).times(180f.div(Math.PI)).toFloat()+90f
            }else{
                return Math.atan(Math.abs(y-widthSize.toFloat()/2).toDouble().div(Math.abs(x-widthSize.toFloat()/2))).times(180f.div(Math.PI)).toFloat()+180f
            }
        }
    }

    var ClipBlockList = arrayListOf<ClipBlock>()
}
