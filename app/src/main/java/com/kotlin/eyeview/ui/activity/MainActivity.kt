package com.kotlin.eyeview.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.fragment.Adjust_Fragment
import com.kotlin.eyeview.ui.fragment.AppUsage_Fragment
import com.kotlin.eyeview.ui.fragment.Eye_Fragment
import com.kotlin.eyeview.ui.fragment.We_Fragment
import com.kotlin.eyeview.ui.useapptime.AppUsageActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.kotlin.eyeview.ui.useapptime.UseActivity

//import useapptime.Use_Fragment

class MainActivity : AppCompatActivity() ,View.OnClickListener{

    //切换
    var mEye_Fragment = Eye_Fragment()
    var mAdjust_Fragment = Adjust_Fragment()
    var mWe_Fragment = We_Fragment()
//    var mUse_Fragment = Use_Fragment()
    var app_Fragment = AppUsage_Fragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment, mEye_Fragment).commit()
//        addFragment(1)
        //点击切换界面
        v1.setOnClickListener(this)
        v2.setOnClickListener(this)
        v3.setOnClickListener(this)

//        v2.setOnClickListener{
////            getWindowHeightOrWidth(this)
////            Log.d("屏幕宽高", "onDraw: $screenWidth $screenHeight")
////            screenList.add(screenWidth)
////            screenList.add(screenHeight)
//            val intent = Intent(this, AppUsageActivity::class.java)
////            intent.putIntegerArrayListExtra("screenList",screenList)
//            startActivity(intent)
//        }


    }

    override fun onClick(view: View?) {
        val fragmentManager = supportFragmentManager //获取FragmentManager实例
        val transaction = fragmentManager.beginTransaction() //开启一个事物
//        hideFragments(transaction)
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
//        transaction.setCustomAnimations(R.anim.slide_left_in,R.anim.slide_right_out,R.anim.slide_right_in,R.anim.slide_left_out)
//        transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_left_out,R.anim.slide_left_in,R.anim.slide_right_out)
        var f : Fragment? = null
        when(view?.id) {
            R.id.v1 -> {
                //如果不是主界面则切换到主界面，
                f = Eye_Fragment()
            }
            R.id.v2 -> f = AppUsage_Fragment()
//            R.id.v2 -> f = Adjust_Fragment()
//            R.id.v2 -> f = Use_Fragment()
            R.id.v3 -> f = We_Fragment()
        }

        //切换
        if (f != null) {
            Log.d("测试点击", "onClick: "+f)
            transaction.replace(R.id.fragment, f)
        }
        transaction.commit() //提交事务

//        when (view!!.id) {
//            R.id.v1 -> addFragment(1)
//            R.id.v2 -> addFragment(2)
//            R.id.v3 -> addFragment(3)
//        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when(requestCode){
//            takePhoto -> {
//                if (resultCode == Activity.RESULT_OK){
//                    val intent = Intent(this, CameraActivity::class.java)
//                    //直接传bitmap过大会报错，所以在activity之间传送uri最后得到图片
//                    intent.data = imageUri
//                    startActivity(intent)
//                }
//            }
//        }
//    }

//    private fun addFragment(i: Int) {
//        //开启事务，fragment的控制是由事务来实现的
//        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        hideFragments(transaction)
//        when (i) {
//            1 -> {
//                // Toast.makeText(this,"加载页面",Toast.LENGTH_SHORT).show()
//                if (mEye_Fragment == null) {
//                    mEye_Fragment = Eye_Fragment()
//                    transaction.add(R.id.fragment, mEye_Fragment).commit()
//                    Toast.makeText(this, "加载页面", Toast.LENGTH_SHORT).show()
//                } else {
//                    transaction.show(mEye_Fragment)
//                    Toast.makeText(this, "加载", Toast.LENGTH_SHORT).show()
//                }
//            }
//            2 -> {
////                if (mUse_Fragment == null) {
////                    mUse_Fragment = Use_Fragment()
////                    transaction.add(R.id.fragment, mUse_Fragment).commit()
////                } else {
////                    transaction.show(mUse_Fragment)
////                }
//
//                if (mAdjust_Fragment == null) {
//                    mAdjust_Fragment = Adjust_Fragment()
//                    transaction.add(R.id.fragment, mAdjust_Fragment).commit()
//                } else {
//                    transaction.show(mAdjust_Fragment)
//                }
//            }
//            3 -> {
//
//                if (mWe_Fragment == null) {
//                    mWe_Fragment = We_Fragment()
//                    transaction.add(R.id.fragment, mWe_Fragment).commit()
//                } else {
//                    transaction.show(mWe_Fragment)
//                }
//            }
//        }
//        transaction.commitAllowingStateLoss()
//    }


    private fun hideFragments(transaction: FragmentTransaction) {
//        if (mUse_Fragment != null) {
//            transaction.hide(mUse_Fragment)
//        }
        if (app_Fragment != null) {
            transaction.hide(app_Fragment)
        }
        if (mEye_Fragment != null) {
            transaction.hide(mEye_Fragment)
        }
        if (mWe_Fragment != null) {
            transaction.hide(mWe_Fragment)
        }
    }

}