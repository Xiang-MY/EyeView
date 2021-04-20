package com.kotlin.eyeview.ui.fragment

import android.content.ContentValues.TAG
import android.os.Bundle import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.adapter.Brightness

class Adjust_Fragment : Fragment(),SeekBar.OnSeekBarChangeListener{
    lateinit var change: Button

    lateinit var seekBar1: SeekBar
    lateinit var seekBar2: SeekBar
    lateinit var text1: TextView
    lateinit var text2: TextView

    var flag = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.adjust_fragment, container, false)

        change = view.findViewById(R.id.change)

        seekBar1 = view.findViewById(R.id.seekbar1)
        seekBar2 = view.findViewById(R.id.seekbar2)

        text1 = view.findViewById(R.id.text1)
        text2 = view.findViewById(R.id.text2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        seekBar1.setOnSeekBarChangeListener(this)
        seekBar2.setOnSeekBarChangeListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        change.setOnClickListener {
            if (flag == 0){
                change.text = "STOP"
                flag = 1
//                Toast.makeText(context,"关闭",Toast.LENGTH_SHORT).show()
            }else if(flag == 1){
                change.text = "OPEN"
                flag = 0
//                Toast.makeText(context,"开启",Toast.LENGTH_SHORT).show()

            }
        }
//        seekBar2.progress = this.activity?.let { getScreenBrightness(it) }!!
//        进度条适应系统亮度，但始终为100%
//        activity?.let { initView(it) }

//        changeBrightness(seekBar2.progress.toFloat())
         var bright =  Brightness.getSystemBrightness(activity!!.contentResolver)
        Log.d(TAG, "系统亮度: $bright")
//        seekBar2.progress = (bright/4095f*100).toInt()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (seekBar!! == seekBar1){
            text1.text = progress.toString() + "%"
        }else if (seekBar == seekBar2){
            text2.text = progress.toString() + "%"
            //改变系统亮度
            Brightness.changeAppBrightness(this.activity,progress)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }

}
