package com.kotlin.eyeview.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.fragment.Adjust_Fragment
import com.kotlin.eyeview.ui.fragment.Eye_Fragment
import com.kotlin.eyeview.ui.fragment.We_Fragment
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() ,View.OnClickListener{

    //切换
    var mEye_Fragment = Eye_Fragment()
    var mAdjust_Fragment = Adjust_Fragment()
    var mWe_Fragment = We_Fragment()
//    var Blur_Fragment =

    //调用相机
    val takePhoto = 1
    lateinit var imageUri: Uri
    lateinit var outputImage: File
    val TAG = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment,mEye_Fragment).commit()
//        addFragment(1)
        //点击切换界面
        v1.setOnClickListener(this)
//        v2.setOnClickListener(this)
        v3.setOnClickListener(this)

        //点击调用相机
        v2.setOnClickListener {
            Log.d(TAG, "onCreate: 拍照")
            //创建File对象，用于存储拍照后的图片
            //externalCacheDir方法可以得到存储如片的目录
            outputImage = File(externalCacheDir,"output_image.jpg")
            if (outputImage.exists()){
                outputImage.delete()
            }
            outputImage.createNewFile()
            imageUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                FileProvider.getUriForFile(this,"com.example.eyeview.fileprovider",outputImage)
            } else {
                Uri.fromFile(outputImage)
            }
            //启动相机程序
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            Log.d("testt","调用相机")
            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
            startActivityForResult(intent,takePhoto)
//            startActivity(intent)
        }

    }

    override fun onClick(view: View?) {
        val fragmentManager = supportFragmentManager //获取FragmentManager实例
        val transaction = fragmentManager.beginTransaction() //开启一个事物
        var f : Fragment? = null
        when(view?.id) {
            R.id.v1 -> {
                //如果不是主界面则切换到主界面，
                f = Eye_Fragment()
            }
//            R.id.v2 -> f = Adjust_Fragment()
            R.id.v3 -> f = We_Fragment()
        }

        //切换
        if (f != null) {
            transaction.replace(R.id.fragment,f)
        }
        transaction.commit() //提交事务

//        when (view!!.id) {
//            R.id.v1 -> addFragment(1)
//            R.id.v2 -> addFragment(2)
//            R.id.v3 -> addFragment(3)
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            takePhoto -> {
                if (resultCode == Activity.RESULT_OK){
                    val intent = Intent(this, CameraActivity::class.java)
                    //直接传bitmap过大会报错，所以在activity之间传送uri最后得到图片
                    intent.data = imageUri
                    startActivity(intent)
                }
            }
        }
    }

    private fun addFragment(i: Int) {
        //开启事务，fragment的控制是由事务来实现的
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (i) {
            1 -> {
                // Toast.makeText(this,"加载页面",Toast.LENGTH_SHORT).show()
                if (mEye_Fragment == null) {
                    mEye_Fragment = Eye_Fragment()
                    transaction.add(R.id.fragment, mEye_Fragment).commit()
                    Toast.makeText(this,"加载页面", Toast.LENGTH_SHORT).show()
                } else {
                    transaction.show(mEye_Fragment)
                    Toast.makeText(this,"加载", Toast.LENGTH_SHORT).show()
                }
            }
            2 -> {

                if (mAdjust_Fragment == null) {
                    mAdjust_Fragment = Adjust_Fragment()
                    transaction.add(R.id.fragment, mAdjust_Fragment).commit()
                } else {
                    transaction.show(mAdjust_Fragment)
                }
            }
            3 -> {

                if (mWe_Fragment == null) {
                    mWe_Fragment = We_Fragment()
                    transaction.add(R.id.fragment, mWe_Fragment).commit()
                } else {
                    transaction.show(mWe_Fragment)
                }
            }
        }
        transaction.commitAllowingStateLoss()
    }


    private fun hideFragments(transaction: FragmentTransaction) {
        if (mAdjust_Fragment != null) {
            transaction.hide(mAdjust_Fragment)
        }
        if (mEye_Fragment != null) {
            transaction.hide(mEye_Fragment)
        }
        if (mWe_Fragment != null) {
            transaction.hide(mWe_Fragment)
        }
    }

}