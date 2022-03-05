package com.kotlin.eyeview.ui.activity

import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.widget.MediaController
import android.widget.RelativeLayout
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.eyeview.R


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

    }
}