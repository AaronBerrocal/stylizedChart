package com.bluered.stylizedlinechart.utils

import android.graphics.Canvas
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.renderer.XAxisRenderer
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Transformer
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler

class MultipleLineXAxisRenderer(
    viewPortHandler: ViewPortHandler,
    xAxis: XAxis,
    trans: Transformer
): XAxisRenderer(viewPortHandler, xAxis, trans)  {

    override fun drawLabel(
        c: Canvas?,
        formattedLabel: String?,
        x: Float,
        y: Float,
        anchor: MPPointF?,
        angleDegrees: Float
    ) {
        val multipleLines = formattedLabel?.split("\n")
        if(!multipleLines.isNullOrEmpty()){
            for(i in multipleLines.indices){
                Utils.drawXAxisValue(
                    c,
                    multipleLines[i],
                    if(angleDegrees == 0f) x else x + i * mAxisLabelPaint.textSize,
                    y + i * mAxisLabelPaint.textSize,
                    mAxisLabelPaint,
                    anchor,
                    angleDegrees
                )
            }
        }
    }
}