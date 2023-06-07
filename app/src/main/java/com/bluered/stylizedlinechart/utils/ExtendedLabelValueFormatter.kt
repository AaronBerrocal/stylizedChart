package com.bluered.stylizedlinechart.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class ExtendedLabelValueFormatter : ValueFormatter() {

    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return if (value.toInt() % 4 == 0) String.format("%.1f \n Multiple of 4", value) else value.toString()
    }
}