package com.bluered.stylizedlinechart.domain

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bluered.stylizedlinechart.utils.ExtendedLabelValueFormatter
import com.bluered.stylizedlinechart.utils.MultipleLineXAxisRenderer
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
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
                        this?.colorGradingSetup(element)
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
        val data = LineDataSet(chartEntries, label).apply {
            axisDependency = YAxis.AxisDependency.LEFT
            lineWidth = 2f
            color = Color.GRAY
            circleRadius = 4f
            setDrawFilled(false)
            setDrawCircles(false)
            setDrawValues(false)
        }

        chart.data = LineData(data)
        chart.apply {
            setTouchEnabled(true)
            setPinchZoom(false)
            this.description.text = description
            setNoDataText("No data available")
            isAutoScaleMinMaxEnabled = true

            this.xAxis.apply {
                valueFormatter = ExtendedLabelValueFormatter()
                position = XAxis.XAxisPosition.BOTTOM_INSIDE
                setDrawGridLines(false)
            }

            setXAxisRenderer(
                MultipleLineXAxisRenderer(
                    this.viewPortHandler,
                    this.xAxis,
                    this.getTransformer(YAxis.AxisDependency.LEFT)
                )
            )

            this.axisLeft.apply {
                setDrawAxisLine(false)
            }

            this.axisRight.apply {
                setDrawLabels(false)
                setDrawAxisLine(false)
            }

            this.legend.apply {
                isEnabled = false
            }

            invalidate()
        }


    }

    private fun Drawable?.colorGradingSetup(x: Float) {
        this?.setTint(
            when {
                x < 30f -> {
                    colorsConfig[1]
                }

                x > 30f && x <= 50f -> {
                    colorsConfig[2]
                }

                x > 50f && x <= 70f -> {
                    colorsConfig[3]
                }

                x > 70f -> {
                    colorsConfig[4]
                }

                else -> {
                    colorsConfig[0]
                }
            }
        )
    }

}