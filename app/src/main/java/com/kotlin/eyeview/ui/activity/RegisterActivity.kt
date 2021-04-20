package com.kotlin.eyeview.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.bmob.v3.Bmob
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.Phone
import com.kotlin.eyeview.ui.SetPassword
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Bmob.initialize(this, "001fc45fef84fa200feb641c2d8bbcd2")
        sendVerification.setOnClickListener {
            val userphone=userPhone.text.toString()
            val isphone=Phone().isMobile(userphone)
            if(userphone!=null&&isphone){
                toast("验证码默认为：1234")
            }else{
                toast("请输入正确的手机号")
            }
        }
        Submit.setOnClickListener {
            val userphone=userPhone.text.toString()
            val isphone=Phone().isMobile(userphone)
            val verify=verificationCode.text.toString()
            if (isphone&&verify=="1234"){
                val intent= Intent(this, SetPassword::class.java)
                intent.putExtra("register_phone",userphone)
                startActivity(intent)
            }else{
                toast("手机号格式或验证码错误")
            }
        }
    }


}