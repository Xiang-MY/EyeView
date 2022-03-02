package com.kotlin.eyeview.ui.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.RenderScriptGlassBlur
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.*

class CameraActivity : AppCompatActivity() {

    private var originimg: ImageView? = null

    /**
     * 进度条SeekBar
     */
    private var mSeekBar: SeekBar? = null

    /**
     * 显示进度的文字
     */
    private val mProgressTv: TextView? = null

    /**
     * 透明度
     */
    private var mAlpha = 0

    /**
     * 原始图片
     */
    private var mTempBitmap: Bitmap? = null

    /**
     * 模糊后的图片
     */
    private var mFinalBitmap: Bitmap? = null

    var data: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
//        val bitmap = intent.extras!!.getParcelable<Bitmap>("data")
//        PhotoimageView.setImageBitmap(bitmap)

        originimg = origin_img
        mSeekBar = seekbar

        //通过传送的uri得到图片并显示
        data = intent.data
//        origin_img.setImageURI(data)
        originimg!!.setImageURI(data)

        //获取图片
        mTempBitmap = BitmapFactory.decodeStream(data?.let { contentResolver.openInputStream(it) })
//        mFinalBitmap = BlurBitmap.blur(this,mTempBitmap)
//
//        //填充模糊后的图像和原图
////        blured_img.setImageBitmap(mFinalBitmap)
//        origin_img.setImageBitmap(mTempBitmap)
//
////        mSeekBar = seekbar
//
//        //处理seekbar滑动事件
        setSeekBar()

    }

    /**
     * 处理seekbar滑动事件
     */
    private fun setSeekBar() {
//        seekbar!!.max = 100
//        seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
//            override fun onProgressChanged(
//                seekBar: SeekBar,
//                progress: Int,
//                fromUser: Boolean
//            ) {
//                mAlpha = progress
//                origin_img.setAlpha((255 - mAlpha * 2.55).toInt())
//                mProgressTv!!.text = mAlpha.toString()
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar) {}
//            override fun onStopTrackingTouch(seekBar: SeekBar) {}
//        })
        mSeekBar!!.max = 25
        mSeekBar!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int,
                fromUser: Boolean
            ) {
//                val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.test10)
                var prebitmap = BitmapFactory.decodeStream(data?.let { contentResolver.openInputStream(it) })
                val bitmap = rotateBitmap(prebitmap)
                var progress = progress

//                firstImageView.setImageBitmap(tmpBitmap)

                if (progress < 1) {
                    progress = 1
                }
                val blurBitmap: Bitmap =
                    RenderScriptGlassBlur.handleGlassblur(applicationContext, bitmap, progress)
                originimg!!.setImageBitmap(blurBitmap)
                progress_tv.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }

    private fun rotateBitmap(bitmap: Bitmap): Bitmap{
        val matrix = Matrix()
        matrix.reset()
        matrix.setRotate(90f)
        var bitmap = BitmapFactory.decodeStream(data?.let { contentResolver.openInputStream(it) })
        bitmap = Bitmap.createBitmap(
            bitmap,
            0,
            0,
            bitmap.getWidth(),
            bitmap.getHeight(),
            matrix,
            true
        )
        return bitmap
    }

    private fun getFile(bitmap: Bitmap): File? {
        val baos: ByteArrayOutputStream? = null
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos)
        val imgfile: File? = File(Environment.getExternalStorageState() + "/temp.jpg")
        try {
            imgfile!!.createNewFile()
            val fos = FileOutputStream(imgfile)
            val `is`: InputStream = ByteArrayInputStream(baos!!.toByteArray())
            var x = 0
            val b = ByteArray(1024 * 100)
            while (`is`.read(b).also { x = it } != -1) {
                fos.write(b, 0, x)
            }
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return imgfile
    }


}