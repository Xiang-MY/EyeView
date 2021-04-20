package com.kotlin.eyeview.ui.Achromatopsia.common

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
}