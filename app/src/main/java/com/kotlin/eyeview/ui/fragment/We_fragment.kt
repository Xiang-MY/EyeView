package com.kotlin.eyeview.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.Achromatopsia.activity.QuestionActivity
import com.kotlin.eyeview.ui.Achromatopsia.common.Constants
import kotlinx.android.synthetic.main.we_fragment.*


class We_Fragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.we_fragment, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        challenge.setOnClickListener {
                val intent = Intent(activity, QuestionActivity::class.java)
                val questionCount = "2"
                questionCount.toInt()
                intent.putExtra(Constants.QUESTION_COUNT, questionCount)
                startActivity(intent)
            }
    }

}