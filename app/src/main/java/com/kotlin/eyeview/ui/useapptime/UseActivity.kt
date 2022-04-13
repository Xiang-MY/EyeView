package com.kotlin.eyeview.ui.useapptime

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.eyeview.R
import com.kotlin.eyeview.databinding.ActivityUseBinding
import com.kotlin.eyeview.ui.activity.TestActivity
import com.kotlin.eyeview.ui.activity.VideoActivity
import kotlinx.android.synthetic.main.activity_use.*

class UseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_use)
        //        setContentView(R.layout.activity_use);
        val bind = ActivityUseBinding.inflate(layoutInflater)
        setContentView(bind.root)
        //        Log.d("报错", "onCreate: ");
//        bind.btnAppUsage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("报错", "onCreate: ");
//                Toast.makeText(getBaseContext(),"跳转",Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(UseActivity.this,AppUsageActivity.class);
//                startActivity(intent);
//            }
//        });
        bind.btnAppUsage.setOnClickListener {
            startActivity(
                Intent(
                    this@UseActivity,
                    TestActivity::class.java
                )
            )
//            Toast.makeText(this,"跳转跳转",Toast.LENGTH_SHORT).show()
        }
//        btn_app_usage.setOnClickListener {
////            val t = ToastTest()
////            t.Toast("跳转跳转")
////            Log.d("报错", "onCreate: ")
//            startActivity(
//                Intent(
//                    this@UseActivity,
//                    TestActivity::class.java
//                )
//            )
//            Toast.makeText(this,"跳转跳转",Toast.LENGTH_SHORT).show()
//        }
//        bind.btnAppUsage.setOnClickListener(v -> startActivity(new Intent(UseActivity.this, AppUsageActivity.class)));
    }
}