package com.bluered.stylizedlinechart.domain

import android.content.Context
import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class StylizedChartConfiguration {

    private val sampleTimes = arrayListOf(
        40f,
        50f,
        60f,
        45f,
        70f,
        90f,
        20f,
        55f,
        75f,
        35f,
    )

    private val colorsConfig = arrayListOf(
        Color.BLACK,
        Color.RED,  // y <= 30
        Color.YELLOW, // 30 < y <= 50
        Color.GREEN, // 50 < y <= 70
        Color.BLUE // 70 < y
    )

    fun chartDataSetup(
        context: Context,
        @DrawableRes drawableRes: Int
    ): List<Entry> {
        val chartEntries = arrayListOf<Entry>()

        sampleTimes.forEachIndexed { index, element ->
            chartEntries.add(
                Entry(
                    (index + 1).toFloat(),
                    element,
                    ContextCompat.getDrawable(
                        context,
                        drawableRes
                    ).apply {
                        this?.setTint(
                            when {
                                element < 30f -> {
                                    colorsConfig[1]
                                }

                                element > 30f && element <= 50f -> {
                                    colorsConfig[2]
                                }

                                element > 50f && element <= 70f -> {
                                    colorsConfig[3]
                                }

                                element > 70f -> {
                                    colorsConfig[4]
                                }

                                else -> {
                                    colorsConfig[0]
                                }
                            }
                        )
                    }
                )
            )

        }

        return chartEntries

    }

    fun chartSetup(
        chart: LineChart,
        chartEntries: List<Entry>,
        label: String,
        description: String,

        ) {
        val dataSet = LineDataSet(chartEntries, label)
        dataSet.lineWidth = 2f
        dataSet.color = Color.GRAY
        dataSet.circleRadius = 4f
        dataSet.setDrawFilled(false)

        chart.data = LineData(dataSet)
        chart.setTouchEnabled(true)
        chart.setPinchZoom(false)
        chart.description.text = description
        chart.setNoDataText("No data available")
        chart.isAutoScaleMinMaxEnabled = true

        chart.invalidate()

    }

}