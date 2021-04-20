package com.kotlin.eyeview.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.fragment.Adjust_Fragment
import com.kotlin.eyeview.ui.fragment.Eye_Fragment
import com.kotlin.eyeview.ui.fragment.We_Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() ,View.OnClickListener{

    var mEye_Fragment = Eye_Fragment()
    var mAdjust_Fragment = Adjust_Fragment()
    var mWe_Fragment = We_Fragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.fragment,mEye_Fragment).commit()
//        addFragment(1)
        v1.setOnClickListener(this)
        v2.setOnClickListener(this)
        v3.setOnClickListener(this)


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
            R.id.v2 -> f = Adjust_Fragment()
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