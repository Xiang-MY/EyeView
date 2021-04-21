package com.kotlin.eyeview.ui.detection.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.detection.common.Constants
import com.kotlin.eyeview.ui.activity.MainActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val correctCount = intent.getStringExtra(Constants.CORRECT_COUNT)
        val wrongCount = intent.getStringExtra(Constants.WRONG_COUNT)

        result_text2.text = "恭喜您，您的成绩是："
        result_count.text = "答对${correctCount}道，答错${wrongCount}道"

        result_button.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}