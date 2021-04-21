package com.kotlin.eyeview.ui.detection.common

import com.kotlin.eyeview.R
import kotlin.collections.ArrayList

/**
 * @author: ffzs
 * @Date: 2020/9/10 上午10:31
 */

object Constants {

    const val QUESTION_COUNT = "question_count"
    const val CORRECT_COUNT = "correct_count"
    const val WRONG_COUNT = "wrong_count"

//色盲
    fun getQuestions(count: Int): MutableList<Question> {
        val questions = ArrayList<Question>()
        val que1 = Question(1, "选出你看到的",
            R.drawable.nine, "7", "2", "9", "8", 3)
        val que2 = Question(2, "选出你看到的",
            R.drawable.seventy_four,"42", "38", "74", "103", 3)

        questions.addAll(
            listOf(que1, que2)
        )

        questions.shuffle()
        return questions.subList(0, count)
    }
    //散光
    fun getAstigmatism(count: Int): MutableList<Question> {
        val astigmatism = ArrayList<Question>()
        val que1 = Question(1, "选出你看到的!!!!!",
                R.drawable.nine, "7", "2", "9", "8", 3)
        val que2 = Question(2, "选出你看到的",
                R.drawable.seventy_four,"42", "38", "74", "103", 3)

        astigmatism.addAll(
                listOf(que1, que2)
        )
        astigmatism.shuffle()
        return astigmatism.subList(0, count)
    }

    //敏感度
    fun getSensitivity(count: Int): MutableList<Question> {
        val astigmatism = ArrayList<Question>()
        val que1 = Question(1, "选出你看到的敏感度度度度!!",
            R.drawable.nine, "7", "2", "9", "8", 3)
        val que2 = Question(2, "选出你看到的",
            R.drawable.seventy_four,"42", "38", "74", "103", 3)

        astigmatism.addAll(
            listOf(que1, que2)
        )
        astigmatism.shuffle()
        return astigmatism.subList(0, count)
    }

    //老花眼
    fun getPrebyopia(count: Int): MutableList<Question> {
        val prebyopia= ArrayList<Question>()
        val que1 = Question(1, "老花眼老花眼！！!!",
                R.drawable.nine, "7", "2", "9", "8", 3)
        val que2 = Question(2, "选出你看到的",
                R.drawable.seventy_four,"42", "38", "74", "103", 3)

        prebyopia.addAll(
                listOf(que1, que2)
        )
        prebyopia.shuffle()
        return prebyopia.subList(0, count)
    }

    //压力
    fun getPressure(count: Int): MutableList<Question> {
        val pressure= ArrayList<Question>()
        val que1 = Question(1, "压力压力!!",
                R.drawable.nine, "7", "2", "9", "8", 3)
        val que2 = Question(2, "选出你看到的",
                R.drawable.seventy_four,"42", "38", "74", "103", 3)

        pressure.addAll(
                listOf(que1, que2)
        )
        pressure.shuffle()
        return pressure.subList(0, count)
    }


}