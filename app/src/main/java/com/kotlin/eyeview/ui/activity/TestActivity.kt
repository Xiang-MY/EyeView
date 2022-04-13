package com.kotlin.eyeview.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.useapptime.UseActivity
import kotlinx.android.synthetic.main.activity_test.*
//Toast.makeText(context,"切换${c}",Toast.LENGTH_SHORT).show()
class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        btntest.setOnClickListener {
            val intent = Intent(this@TestActivity,UseActivity::class.java)
            startActivity(intent)
        }
    }
}