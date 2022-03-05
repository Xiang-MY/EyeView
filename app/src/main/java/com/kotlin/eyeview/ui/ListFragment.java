package com.kotlin.eyeview.ui;//package com.example.eyedt.ui;
//
//import android.content.res.Resources;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.provider.Settings;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.BaseAdapter;
//import android.widget.SeekBar;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.core.math.MathUtils;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import com.example.eyedt.R;
//import com.example.eyedt.ui.adapter.BarChartManager;
//import com.example.eyedt.ui.adapter.PieChartManagger;
//import com.example.eyedt.ui.adapter.RadarChartManager;
//import com.github.mikephil.charting.data.BarEntry;
//import com.github.mikephil.charting.data.PieEntry;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ListFragment extends Fragment {
//    RecyclerView recyclerView;
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    private void changeBrightness(float change) {
//        float old = getWindow().getAttributes().screenBrightness;
//        float none = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE; // -1.0f
//        if (old == none) {
//            // 取到了默认值
//            try {
//                int current = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
//                if (current <= getBrightnessMax()) {
//                    old = (current * 1f) / getBrightnessMax();
//                }
//            } catch (Exception ignore) {
//            }
//        }
//        if (old == none|| old <= 0) {
//            // 如果没有取值成功，那么就默认设置为一半亮度，防止突然变得很亮或很暗
//            old = 0.5f;
//        }
//
//        float newBrightness = MathUtils.clamp(old + change, 0.01f, 1f);
//        WindowManager.LayoutParams params = getWindow().getAttributes();
//        params.screenBrightness = newBrightness;
//        getWindow().setAttributes(params);
//    }
//
//
//    private void initView() {
//        sb_brightness = (SeekBar) findViewById(R.id.sb_brightness);
//        int brightness = getBrightness();
//        sb_brightness.setMax(255);
//        sb_brightness.setProgress(brightness);
//        brightness_value.setText(brightness+"");
//        Log.e(TAG, "当前亮度:" + brightness);
//        changeAppBrightness(brightness);
//        sb_brightness.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                changeAppBrightness(i);
//                setBrightness(i);
//                saveBrightness(i);
//                brightness_value.setText(i+"");
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        try {
//            Resources system = Resources.getSystem ();
//            int resId = system.getIdentifier ("config_screenBrightnessSettingMaximum", "integer", "android");
//            if (resId != 0) {
//                return system.getInteger(resId);
//            }
//        } catch (Exception ignore) {
//        }
//        return 255;
//    }
//
//    publi changeAppBrightness(int brightness) {
//        Window window = this.getWindow();
//        WindowManager.LayoutParams lp = window.getAttributes();
//        if (brightness == -1) {
//            lp.screenBrightness = WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
//        } else {
//            lp.screenBrightness = (brightness <= 0 ? 1 : brightness) / 255f;
//        }
//        window.setAttributes(lp);
//    }
//
//    private void show_reader_chart() {
//
//        RadarChartManager radarChartManager = new RadarChartManager(this, readar_chart);
//        List<String> xData = new ArrayList<>();
//        List<List<Float>> yDatas = new ArrayList<>();
//        List<String> names = new ArrayList<>();
//        names.add("东城区");
//        names.add("朝阳区");
//        names.add("西城");
//        names.add("丰台区");
//        names.add("石景山");
//        names.add("房山区");
//
//        xData.add("总体");
//        xData.add("交通设施");
//        xData.add("评价");
//        xData.add("平均");
//        xData.add("交通治理");
//        xData.add("交通需求");
//        xData.add("交通拥堵指数");
//        List<Integer> colors = new ArrayList<>();
//
//        for (int i = 0; i < 6; i++) {
//            List<Float> nData = new ArrayList<>();
//            for (int j = 0; j < 7; j++) {
//                nData.add((float) (Math.random() * 20));
//            }
//            yDatas.add(nData);
//        }
//
//        colors.add(Color.parseColor("#fbd06a"));
//        colors.add(Color.parseColor("#f69a40"));
//        colors.add(Color.parseColor("#ff5d52"));
//        colors.add(Color.parseColor("#e71f19"));
//        colors.add(Color.parseColor("#ff9b43"));
//        colors.add(Color.parseColor("#8eb9fb"));
//        radarChartManager.showRadarChart(xData, yDatas, names, colors);
//    }
//
//    private void show_bar_chart_1() {
//        BarChartManager barChartManager = new BarChartManager(bar_chart1);
//        List<BarEntry> yVals = new ArrayList<>();
//        yVals.add(new BarEntry(1f, 80f));
//        yVals.add(new BarEntry(2f, 50f));
//        yVals.add(new BarEntry(3f, 60f));
//        yVals.add(new BarEntry(4f, 60f));
//        yVals.add(new BarEntry(5f, 70f));
//        yVals.add(new BarEntry(6f, 80f));
//        String label = "";
//        barChartManager.showBarChart(yVals, label, Color.parseColor("#00ff00"));
//    }
//
//    //设置数据
//    private void show_pie_chart_1() {
////设置每份所占数量
//        List<PieEntry> yvals = new ArrayList<>();
//        yvals.add(new PieEntry(2.0f, "本科"));
//        yvals.add(new PieEntry(3.0f, "硕士"));
//        yvals.add(new PieEntry(4.0f, "博士"));
//        yvals.add(new PieEntry(5.0f, "大专"));
//        yvals.add(new PieEntry(1.0f, "其他"));
//// 设置每份的颜色
//        List<Integer> colors = new ArrayList<>();
//        colors.add(Color.parseColor("#6785f2"));
//        colors.add(Color.parseColor("#675cf2"));
//        colors.add(Color.parseColor("#496cef"));
//        colors.add(Color.parseColor("#aa63fa"));
//        colors.add(Color.parseColor("#f5a658"));
//        PieChartManagger pieChartManagger=new PieChartManagger(pie_chart1);
//        pieChartManagger.showRingPieChart(yvals,colors);
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.list_fragment,container,false);
//        return root;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        recyclerView = view.findViewById(R.id.recyclerview);
//        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
//
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//
//                int lastVisibleItem = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
//                int totalItemCount = mLayoutManager.getItemCount();//总条目
//                //lastVisibleItem >= totalItemCount - 5 表示剩下5个item实现预加载
//                // dy>0 表示向下滑动,滑动距离
//                if (totalItemCount >= 10 && lastVisibleItem >= totalItemCount - 5 && dy > 0) {
//
//                    if (mIsLoadingMore) {
//                        ToastUtils.showWithShort(getApplicationContext(), "已经预加载了!");
//                    } else {
//                        StartLoadMoreTask();//加载更多
//                    }
//                }
//            }
//        });
//    }
//}

