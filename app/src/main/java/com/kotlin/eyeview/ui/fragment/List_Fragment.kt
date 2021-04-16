package com.kotlin.eyeview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.adapter.Bean_mainList
import com.kotlin.eyeview.ui.adapter.List_Adapter

class List_Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    var list = ArrayList<Bean_mainList>()  //存放展示的图片和文字列表

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list__fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val item = Bean_mainList(
            "aaaaa",
            "http://photocq.photo.store.qq.com/psc?/V11qzdNF0Ok9NO/8x9yFjEE1.JqOZi1gudBZ3PAkxxyJeJOZiA1WJ9wP.iRGYy9Ygv7MVDANOA0Gm8kT19mPBUtMUf7185vKTw.RZCt.hgr0oHSLr0SSZDH1GU!/b&bo=wAOHA8ADhwMRGS4!&rf=viewer_4"
        )
        list.add(item)
        list.add(item)

        val layoutManager = GridLayoutManager(context,2)  //设置排布方式
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = List_Adapter(
            R.layout.list_item,
            list
        )//适配器

    }
}