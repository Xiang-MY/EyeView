package com.kotlin.eyeview.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.bmob.v3.Bmob
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_set_password.*
import kotlinx.android.synthetic.main.activity_set_password.Submit
import org.jetbrains.anko.toast


class SetPassword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_password)
        Bmob.initialize(this, "001fc45fef84fa200feb641c2d8bbcd2")
        Submit.setOnClickListener {
            val setpassword=setPassword.text.toString()
            val confirmpassword=confirmPassword.text.toString()
            if (setpassword==confirmpassword){
               savePerson()
                toast("注册成功")
                val intent= Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }else{
                toast("密码不一致")
            }
        }
    }

    fun savePerson(){
        val myUser = User()
        myUser.setUsername(intent.getStringExtra("register_phone").toString())
        myUser.setPassword(setPassword.text.toString())
        toast(setPassword.text.toString())
        myUser.signUp(object : SaveListener<Any?>() {
            fun onSuccess() {
                Toast.makeText(this@SetPassword, "注册成功", Toast.LENGTH_SHORT).show()
            }

            fun onFailure(i: Int, s: String?) {
                Toast.makeText(this@SetPassword, "注册失败", Toast.LENGTH_SHORT).show()
            }

            override fun done(p0: Any?, p1: BmobException?) {

            }
        })
    }
}