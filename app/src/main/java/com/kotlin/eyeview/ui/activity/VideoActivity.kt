package com.kotlin.eyeview.ui.activity

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.net.Uri.parse
import android.os.Build
import android.os.Bundle
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.Log
import android.widget.MediaController
import android.widget.RelativeLayout
import android.widget.Toast
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.eyeview.R
import kotlinx.android.synthetic.main.activity_video.*


class VideoActivity : AppCompatActivity() {
    lateinit var uri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        //得到从list_Fragment传递的信息
        val intent = intent
        val bundle = intent.extras
//        val data = intent.getStringExtra("data")
        val videoView = findViewById<VideoView>(R.id.videoView)
        //Creating MediaController
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        //设置播放加载路径
//        if (data.equals("1")){
//            uri = parse("android.resource://"+packageName+"/"+R.raw.video)
//        }else{
//            uri = parse("android.resource://"+packageName+"/"+R.raw.v)
//        }
        if (bundle!!.containsKey("data")){
            val data = intent.getStringExtra("data")
            if (data!! == "1"){
                //R.raw.exercise1不能有大写否则引用不到
                uri = parse("android.resource://"+packageName+"/"+R.raw.exercise1)
            }else if (data == "2"){
                uri = parse("android.resource://"+packageName+"/"+R.raw.exercise2)
            }else if (data == "3"){
                uri = parse("android.resource://"+packageName+"/"+R.raw.exercise3)
            }else if (data == "4"){
                uri = parse("android.resource://"+packageName+"/"+R.raw.exercise4)
            }
        }else if (bundle.containsKey("position")){
            val position = Integer.parseInt(intent.getStringExtra("position")) + 1
            if (position % 4 == 1){
                //R.raw.exercise1不能有大写否则引用不到
                uri = parse("android.resource://"+packageName+"/"+R.raw.semangbeilunvideo)
            }else if (position % 4 == 2){
                uri = parse("android.resource://"+packageName+"/"+R.raw.huyanvideo)
            }else if (position % 4 == 3){
                uri = parse("android.resource://"+packageName+"/"+R.raw.semangworldvideo)
            }else if (position % 4 == 0){
                uri = parse("android.resource://"+packageName+"/"+R.raw.jinshisanguangvideo)
            }
        }
//        //Setting MediaController and URI, then starting the videoView
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        //这里用相对布局包裹videoview 实现视频全屏播放
        var layoutParams: RelativeLayout.LayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        videoView.layoutParams = layoutParams

        //播放
        videoView.start()
//        Toast.makeText(applicationContext,"播放成功",Toast.LENGTH_SHORT).show()
//        button.setOnClickListener {
//            screenshot()
//        }
        val c = 10;
        if (c == 10){
            screenshot()
        }


    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun screenshot() {
        // 获取屏幕
        val dView = window.decorView
        dView.isDrawingCacheEnabled = true
        dView.buildDrawingCache()
        val bmp = dView.drawingCache

        if (bmp != null) {
            try {
//                Log.d("成功", "screenshot: ")
                Toast.makeText(applicationContext,"截图成功",Toast.LENGTH_LONG).show()
                val blurmap = blurBitmap(baseContext, bmp)

//                screenImage.setImageBitmap(blurmap)
            } catch (e: Exception) {
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun blurBitmap(context: Context?, bitmap: Bitmap): Bitmap? {
        //用需要创建高斯模糊bitmap创建一个空的bitmap
        val outBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
        // 初始化Renderscript，该类提供了RenderScript context，创建其他RS类之前必须先创建这个类，其控制RenderScript的初始化，资源管理及释放
        val rs = RenderScript.create(context)
        // 创建高斯模糊对象
        val blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
        // 创建Allocations，此类是将数据传递给RenderScript内核的主要方 法，并制定一个后备类型存储给定类型
        val allIn = Allocation.createFromBitmap(rs, bitmap)
        val allOut = Allocation.createFromBitmap(rs, outBitmap)
        //设定模糊度(注：Radius最大只能设置25.f)
        blurScript.setRadius(10f)
        // Perform the Renderscript
        blurScript.setInput(allIn)
        blurScript.forEach(allOut)
        // Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap)
        // recycle the original bitmap
        // bitmap.recycle();
        // After finishing everything, we destroy the Renderscript.
        rs.destroy()
        return outBitmap
    }
}