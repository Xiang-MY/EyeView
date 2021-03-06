package com.kotlin.eyeview.ui.detection.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.detection.common.Constants
import com.kotlin.eyeview.ui.detection.common.Question
import kotlinx.android.synthetic.main.activity_question.*

class AstigmatismActivity  : AppCompatActivity() , View.OnClickListener{


    private var mCurrentPosition = 0
    private var submitTimes = 0
    private var correctCount = 0
    private var correctId:Int? = null
    private var mSelectedOption: TextView? = null
    private var mQuestionList:MutableList<Question>? = null
    private var options:List<TextView> = ArrayList()
    private var questionCount = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        questionCount = 2
        mQuestionList = Constants.getAstigmatism(questionCount)
        options = listOf<TextView>(app_option1, app_option2, app_option3, app_option4)
        options.forEach{
            it.setOnClickListener(this)
        }
        app_submit.setOnClickListener(this)
        setQuestion()
    }


    @SuppressLint("SetTextI18n")
    private fun setQuestion () {
        mCurrentPosition += 1

        if (mCurrentPosition > mQuestionList!!.size) {
            val intent = Intent(this@AstigmatismActivity, ResultActivity::class.java)
            intent.putExtra(Constants.CORRECT_COUNT, correctCount.toString())
            intent.putExtra(Constants.WRONG_COUNT, (mQuestionList!!.size-correctCount).toString())
            intent.putExtra(Constants.QUESTION_COUNT, questionCount)
            startActivity(intent)
            finish()
            return
        }

        options.forEach { setDefaultOption(it) }
        val question = mQuestionList!![mCurrentPosition-1]
        correctId = question.correctAnswer
//        Toast.makeText(this, "${question.correctAnswer}", Toast.LENGTH_SHORT).show()

        app_progress_bar.progress = mCurrentPosition
        app_progress_bar.max = mQuestionList!!.size
        app_progress.text = "$mCurrentPosition/${mQuestionList!!.size}"

        app_question.text = question.question
        app_image.setImageResource(question.image)
        listOf(question.option1, question.option2, question.option3, question.option4)
                .forEachIndexed { i, value -> options[i].text = value }
    }


    private fun setDefaultOption (view: TextView) {
        view.setTextColor(ContextCompat.getColor(this, R.color.text_color))
        view.typeface = Typeface.DEFAULT
        view.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
    }

    override fun onClick(p0: View?) {
        if (p0?.id == app_submit.id && mSelectedOption != null) {
            p0 as Button
            when(submitTimes) {
                0 -> {
                    answer()
                    submitTimes = 1
                    if (mCurrentPosition == mQuestionList!!.size) {
                        p0.text = "??????"
                    }else{
                        p0.text = "???????????????"
                    }
                }
                1 -> {
                    setQuestion()
                    submitTimes = 0
                    mSelectedOption = null
                    p0.text = "??????"
                }
            }
            submitTimes
        }
        else {
            mSelectedOption?.let { setDefaultOption(it) }
            mSelectedOption = p0 as TextView
            mSelectedOption?.let { setSelectedOption(it) }
        }
    }

    private fun setSelectedOption (view: TextView) {
        view.setTextColor(ContextCompat.getColor(this, R.color.text_color))
        view.typeface = Typeface.DEFAULT_BOLD
        view.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun answer () {
        val selectedId = options.indexOf(mSelectedOption) + 1
        if (selectedId == correctId) {
            mSelectedOption?.background = ContextCompat.getDrawable(this, R.drawable.corrent_option_border_bg)
            correctCount++
        }
        else{
            mSelectedOption?.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)
            options[correctId!!-1].background = ContextCompat.getDrawable(this, R.drawable.corrent_option_border_bg)
        }
    }

}