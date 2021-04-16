package com.kotlin.eyeview.ui.activity


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.bmob.v3.Bmob
import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.google.android.material.snackbar.Snackbar
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.User
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast


class LoginActivity : AppCompatActivity() {
    var t:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Bmob初始化
        Bmob.initialize(this, "001fc45fef84fa200feb641c2d8bbcd2")

        //登录点击
        Login.setOnClickListener {
            val user = User()
            //此处替换为你的用户名
            user.username = username.text.toString()
            //此处替换为你的密码
            user.setPassword(passwprd.text.toString())
            user.login(object : SaveListener<Any?>() {
                override fun done(p0: Any?, p1: BmobException?) {
                    if(p1==null){
                       t=true
                        toast("登录成功")
                        val intent=Intent(this@LoginActivity,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        toast("账号或密码错误")
                    }
                }
            })
        }

        //注册点击
        SingIn.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
}