package com.kotlin.eyeview.ui.useapptime;

import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class AppUsageUtil {
    private static final String TAG = "AppUsageUtil";

    //判断是否可以使用AppUsage功能
    public static boolean hasAppUsagePermission(Context context) {
        UsageStatsManager usageStatsManager = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
        }
        if (usageStatsManager == null) {
            return false;
        }
        long currentTime = System.currentTimeMillis();
        // try to get app usage state in last 2 min
        List<UsageStats> stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 2 * 60 * 1000, currentTime);
        return JListKit.isNotEmpty(stats);
    }
    //打开授权页面
    public static void requestAppUsagePermission(Context context) {
//        Toast.makeText(BaseApplication.getApplication(),"获取权限",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.i(TAG, "Start usage access settings activity fail!");
        }
    }
}
