package com.kotlin.eyeview.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.adapter.BarChartManager
import com.kotlin.eyeview.ui.adapter.PieChartManager
import com.kotlin.eyeview.ui.adapter.RadarChartManager
import kotlinx.android.synthetic.main.barchart.*
import java.util.ArrayList

class PersonalData: Fragment() {

    lateinit var pieChart: PieChart
    lateinit var barChart: BarChart
    lateinit var radarchart: RadarChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.personaldata, container, false)
        pieChart = view.findViewById(R.id.pie_chart)
        barChart = view.findViewById(R.id.bar_chart)
        radarchart = view.findViewById(R.id.radar_chart)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        show_pie_chart()
        show_radar_chart()
        show_bar_chart()
    }

    //设置数据
    //饼状图
    private fun show_pie_chart() {

        //设置每份所占数量
        val yvals: MutableList<PieEntry> = ArrayList()
        yvals.add(PieEntry(2.0f, "本科"))
        yvals.add(PieEntry(3.0f, "硕士"))
        yvals.add(PieEntry(4.0f, "博士"))
        yvals.add(PieEntry(5.0f, "大专"))
        yvals.add(PieEntry(1.0f, "其他"))

        // 设置每份的颜色
        val colors: MutableList<Int> = ArrayList()
        colors.add(Color.parseColor("#6785f2"))
        colors.add(Color.parseColor("#675cf2"))
        colors.add(Color.parseColor("#496cef"))
        colors.add(Color.parseColor("#aa63fa"))
        colors.add(Color.parseColor("#f5a658"))
        val pieChartManagger =
            PieChartManager(pieChart)
        pieChartManagger.showSolidPieChart(yvals, colors)
    }

    //柱状图
    private fun show_bar_chart() {
        val barChartManager = BarChartManager(bar_chart)
        val yVals: MutableList<BarEntry> = ArrayList()
        yVals.add(BarEntry(1f, 8f))
        yVals.add(BarEntry(2f, 5f))
        yVals.add(BarEntry(3f, 6f))
        yVals.add(BarEntry(4f, 6f))
        yVals.add(BarEntry(5f, 7f))
        yVals.add(BarEntry(6f, 8f))
        val label = ""
        barChartManager.showBarChart(yVals, label, Color.parseColor("#f5a658"))
    }

    //网状图
    private fun show_radar_chart() {
        val radarChartManager = RadarChartManager(context, radarchart)
        val xData: MutableList<String> =
            ArrayList()
        val yDatas: MutableList<List<Float>> =
            ArrayList()
        val names: MutableList<String> =
            ArrayList()
        names.add("东城区")
        names.add("朝阳区")
        names.add("西城")
        names.add("丰台区")
        names.add("石景山")
        names.add("房山区")
        xData.add("总体")
        xData.add("交通设施")
        xData.add("评价")
        xData.add("平均")
        xData.add("交通治理")
        xData.add("交通需求")
        xData.add("交通拥堵指数")
        val colors: MutableList<Int> = ArrayList()
        for (i in 0..5) {
            val nData: MutableList<Float> =
                ArrayList()
            for (j in 0..6) {
                nData.add((Math.random() * 20).toFloat())
            }
            yDatas.add(nData)
        }
        colors.add(Color.parseColor("#fbd06a"))
        colors.add(Color.parseColor("#f69a40"))
        colors.add(Color.parseColor("#ff5d52"))
        colors.add(Color.parseColor("#e71f19"))
        colors.add(Color.parseColor("#ff9b43"))
        colors.add(Color.parseColor("#8eb9fb"))
        radarChartManager.showRadarChart(xData, yDatas, names, colors)
    }

}