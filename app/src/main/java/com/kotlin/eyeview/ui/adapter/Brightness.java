package com.kotlin.eyeview.ui.adapter;

import android.app.Activity;
import android.content.ContentResolver;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

public class Brightness {
    /*
     * 获取系统亮度
     * */
    public static int getSystemBrightness(ContentResolver cr){
        int systemBrightness=0;
        try {
            systemBrightness = Settings.System.getInt(cr,Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return systemBrightness;//0~4095
    }

    /*
     * 更改当前界面亮度
     * */
    public static void changeAppBrightness(Activity activity, int brightness){
        //取得屏幕属性
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams=window.getAttributes();
        //设置屏幕亮度
        if (brightness==-1){
            layoutParams.screenBrightness=WindowManager.LayoutParams.BRIGHTNESS_OVERRIDE_NONE;
        }else {
            layoutParams.screenBrightness= brightness/255f;
        }
        //重新设置窗口属性
        window.setAttributes(layoutParams);
    }

}
