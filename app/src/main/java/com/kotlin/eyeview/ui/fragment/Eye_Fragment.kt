package com.kotlin.eyeview.ui.fragment

import android.os.Bundle
import com.kotlin.eyeview.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.kotlin.eyeview.ui.PersonalData
import com.kotlin.eyeview.ui.adapter.PageAdapter
import java.util.ArrayList

class Eye_Fragment : Fragment() {

    var viewPager: ViewPager? = null
    var tabLayout: TabLayout? = null
    var pageAdapter: PageAdapter? = null
    var fragmentList: MutableList<Fragment> = ArrayList()
    var titles: MutableList<String> = ArrayList()
    var rootView: View? = null //缓存Fragment view，防止页面切换造成数据清除

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.eye__fragment, container, false)
        viewPager = view.findViewById(R.id.Viewpager)
        tabLayout = view.findViewById(R.id.tab)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initWidget()

    }

    //初始化控件
    private fun initWidget(){
        val listframent1 = List_Fragment()
        val listframent2 = PersonalData()
        fragmentList.add(listframent1)
        fragmentList.add(listframent2)
        titles.add("检测中心")
        titles.add("个人数据")

        //将数据展示在页面上
        tabLayout!!.setupWithViewPager(viewPager)
        pageAdapter =  PageAdapter(childFragmentManager,fragmentList,titles)
        //当把fragmentManager改为childFragmentManager时，切换fragment可以保留初始化页面时的样子，
        //但不会保留刷新情况
        viewPager!!.adapter = pageAdapter
        //viewPager!!.offscreenPageLimit = 2 //设置预加载页面当fragment切换时数据不会消失，但是应用会闪退
    }
}

