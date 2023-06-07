package com.bluered.stylizedlinechart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bluered.stylizedlinechart.databinding.ActivityMainBinding
import com.bluered.stylizedlinechart.domain.StylizedChartConfiguration
import com.github.mikephil.charting.charts.LineChart

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var lChart: LineChart
    private lateinit var chartConfiguration: StylizedChartConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lChart = binding.lcMain
        chartConfiguration = StylizedChartConfiguration()

    }

    override fun onStart() {
        super.onStart()

        val configuredData = chartConfiguration.chartDataSetup(
            context = this,
            drawableRes = R.drawable.twotone_square_24
        )

        chartConfiguration.chartSetup(
            chart = lChart,
            chartEntries = configuredData,
            label = "",
            description = "",
        )

    }

}