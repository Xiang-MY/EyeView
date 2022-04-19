package com.kotlin.eyeview.ui.fragment

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kotlin.eyeview.R
import com.kotlin.eyeview.databinding.AppusageFragmentBinding
import com.kotlin.eyeview.ui.useapptime.*
import java.text.SimpleDateFormat
import java.util.*

class AppUsage_Fragment : Basefragment() {

    private var _mHolder : AppusageFragmentBinding? = null
    private val mHolder get() = _mHolder!!

    private var mItems:
            MutableList<AppUsageBean?>? = null //使用时长列表
    private var mAdapter: CommonRecyclerAdapter<AppUsageBean?>? = null
    private var isGoToGrand = false // 是否去过授权页面
    private var mLoadAppUsageTask: LoadAppUsageTask? = null

    private var maxTime: Long = 0 // 当前列表中 使用最久的APP时间 用于计算进度条百分比

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _mHolder = AppusageFragmentBinding.inflate(inflater, container, false)
        val view = mHolder.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 初始化Tab
        var c = 0
        val tabLayout = mHolder.tabCondition
        for (name in TAB_NAMES) {
            val tab = tabLayout.newTab()
            tab.tag = c
//            Toast.makeText(context,"tag${c}",Toast.LENGTH_SHORT).show()
            tab.view.setOnClickListener {
                    v: View? ->
                    Log.d(TAG, "onActivityCreated: 切换"+c)
                    onTabClick(tab.tag as Int) }
//            tab.view.setOnClickListener {
//                v: View? ->
//                Log.e(TAG, "onActivityCreated: 切换"+c)
//                Toast.makeText(context,"切换${c}",Toast.LENGTH_SHORT).show()
//            }
            tabLayout.addTab(tab.setText(name))
            c++
        }
        // 授权|加载数据
        initData()
    }

    //切换日数据、周数据
    fun onTabClick(position: Int) {
        Log.d(TAG, "onTabClick() called with: position = [$position]")
//        title = TAB_NAMES[position]
        //系统现在的时间
        val currTime = System.currentTimeMillis()
        val dateformat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        Log.d(TAG, "onTabClick: 时间" + dateformat.format(currTime))
        Log.d(TAG, "onTabClick: 零点" + dateformat.format(todayTime0))
        Log.d(TAG, "onTabClick: 昨天" + dateformat.format(lastDay()))
        when (position) {
//            0 -> Toast.makeText(context,"测试0",Toast.LENGTH_SHORT).show()
            0 -> getAppUsage(todayTime0, currTime)
            1 -> {
                val todayTime0 = todayTime0
                getAppUsage(todayTime0 - DateUtils.DAY_IN_MILLIS, todayTime0)
            }
            2 -> getAppUsage(currTime - DateUtils.WEEK_IN_MILLIS, currTime)
            3 -> getAppUsage(currTime - DateUtils.DAY_IN_MILLIS * 30, currTime)
            4 -> getAppUsage(currTime - DateUtils.DAY_IN_MILLIS * 365, currTime)
        }
    }// 获取今天凌晨0点0分0秒的time

    /**
     * @return 今日零点的时间
     */
    private val todayTime0: Long
        private get() {
            // 获取今天凌晨0点0分0秒的time
            val calendar = Calendar.getInstance()
            calendar[calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH], 0, 0] = 0
            return calendar.timeInMillis
        }

    //昨天的现在时刻
    private fun lastDay(): Long {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        return cal.timeInMillis
    }

    //根据时间范围获取应用使用情况
    private fun getAppUsage(beginTime: Long, endTime: Long) {
        val fmt = "yyyy-MM-dd HH:mm:ss"
        //时间范围显示
//        mHolder.tvTimeRange.text = String.format(
//            "(%s - %s)",
//            JDateKit.timeToDate(fmt, beginTime),
//            JDateKit.timeToDate(fmt, endTime)
//        )
        Log.d(
            TAG, "getAppUsage:打印时间范围" + JDateKit.timeToDate(fmt, beginTime) + " " + JDateKit.timeToDate(
                fmt,
                endTime
            )
        )
        // setTitle("数据分析中...");
        //画环形统计图
//        SelfStatistics selfStatistics = mHolder.progress;
//        float datas[] = new float[]{4000,3000,7000,8000};
//        selfStatistics.setDatas(datas);
//        selfStatistics.startDraw();
//        PieChart pieChart = mHolder.progress;
        showLoading("数据分析中...")

        // a task can be executed only once,init is required every time
        mLoadAppUsageTask = LoadAppUsageTask(beginTime, endTime) { list: ArrayList<AppUsageBean?>? ->
            mItems = list
//            Log.d(TAG, "getAppUsage: testttttt")
            initAdapter()
        }
        mLoadAppUsageTask!!.execute()
    }

    private fun initAdapter(){
        maxTime = if (JListKit.isNotEmpty(mItems)){
            Collections.sort(mItems) // 按使用时长排序
            mItems!![0]!!.totalTimeInForeground //转成总的毫秒数
        }else {
            0
        }

//        title = String.format("%s", title)
        //环形统计图设置仅显示前五条数据
        val ringChart = mHolder.progress
        val pieData: MutableList<PieData> = ArrayList()
        //        pieData.add(new PieData("text", (float) Math.random() * 90));
        for (i in mItems!!.indices) {
            if (i >= 5) break
            pieData.add(
                PieData(
                    mItems!![i]!!.appName,
                    mItems!![i]!!.totalTimeInForeground.toFloat()
                )
            )
            //            Log.d(TAG, "initAdapter: " + JDateKit.timeToStringChineChinese(mItems.get(i).getTotalTimeInForeground())); //打印应用使用时长
            Log.d(TAG, "getAppUsage: 应用名称：" + mItems!![i]!!.appName)
            Log.d(
                TAG,
                "initAdapter: 应用总时长：" + mItems!![i]!!.totalTimeInForeground + "  " + JDateKit.timeToStringChineChinese(
                    mItems!![i]!!.totalTimeInForeground
                )
            ) //打印应用使用时长
        }
        ringChart.setData(pieData)

        if (mAdapter == null) {
            val fmt = "yyyy-MM-dd HH:mm:ss"
            mAdapter =
                object : CommonRecyclerAdapter<AppUsageBean?>(R.layout.item_app_usage, mItems) {
                    override fun convert(helper: BaseViewHolder?, item: AppUsageBean?) {
                        //设置应用名称和图标
                        helper!!.setText(R.id.id_tv_app_name, String.format("%s", item!!.appName))
                        val appIcon = item!!.appIcon
                        if (appIcon != null) {
                            helper.setImageDrawable(R.id.id_iv_app_icon, appIcon)
                        } else {
                            helper.setImageResource(R.id.id_iv_app_icon, R.mipmap.ic_launcher)
                        }
                        val totalTimeInForeground = item.totalTimeInForeground
                        helper.setText(
                            R.id.id_tv_time_in_foreground,
                            String.format(
                                "%s",
                                JDateKit.timeToStringChineChinese(totalTimeInForeground),
                                totalTimeInForeground
                            )
                        )
                        //                    helper.setText(R.id.id_tv_last_usage, String.format("上次使用:%s", JDateKit.timeToDate(fmt, item.getLastTimeUsed())));
                        // 计算进度条百分比
                        val percent = item.totalTimeInForeground.toFloat() / maxTime
                        //                    Guideline guideline = helper.getView(R.id.guideline);
//                    guideline.setGuidelinePercent(percent);
//                    loadProgressBar.setProgress((int) percent);
                        val loadProgressBar = helper.getView<ProgressBar>(R.id.loading_progress)
                        loadProgressBar.progress = (percent * 100).toInt()
                    }
                }
            mHolder.rvAppUsage.adapter = mAdapter
            mHolder.rvAppUsage.layoutManager = LinearLayoutManager(activity)
        } else {
            mAdapter!!.setNewInstance(mItems)
        }
        //        PieChart pieChart = mHolder.progress;
//        List<PieData> pieData = new ArrayList<>();
//        for (int i = 0; i < mItems.size(); i++){
//            Log.d(TAG, "initAdapter: " + JDateKit.timeToStringChineChinese(mItems.get(i).getTotalTimeInForeground())); //打印应用使用时长
//            pieData.add(new PieData(mItems.get(i).getAppName(),(float) mItems.get(i).getTotalTimeInForeground()));
//        }
//        pieChart.setData(pieData,1);
        hideLoading()
    }

    private fun initData() {
        if (AppUsageUtil.hasAppUsagePermission(activity)) {
            // 默认加载今天的数据
            isGoToGrand = false
            onTabClick(0)
        } else {
            isGoToGrand = true
            // TODO 这里有点强制开启的意思，实际应用中最好弹出一个对话框让用户知道，并可以选择【授权】或【退出】
            AppUsageUtil.requestAppUsagePermission(activity)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isGoToGrand) { // 如果从应用跳转到了授权，那么返回应用的时候 需要重新执行一次
            initData()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mHolder = null
    }

    companion object {
        private const val TAG = "AppUsageActivity"

        //设置切换tab
        private val TAB_NAMES = arrayOf("今日数据", "昨日数据", "本周数据", "本月数据", "年度数据")
    }
}