//package com.example.eyedt.ui.adapter
////
//import android.content.Context
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.*
//import com.bumptech.glide.Glide
//import com.example.eyedt.R
//
//class GridAdapter(
//    private var context: Context?,
//    private var provinceBeanList: List<Bean_grid>
//): BaseAdapter() {
//
//    inner class GridHolder {
//        lateinit var imageId: ImageView
//        lateinit var text: TextView
//        lateinit var mRelative: RelativeLayout
//    }
////    展示网格页面
//    private var layoutInflater: LayoutInflater? = null
//    override fun getCount(): Int {
//        return provinceBeanList.size
//    }
//
//    override fun getItem(position: Int): Any {
//        return provinceBeanList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getView(
//        position: Int,
//        convertView: View?,
//        parent: ViewGroup
//    ): View? {
//        //与listview构造适配器的方法类似
//        Log.d("测试","错误")
//        var view: View? = null
//        var holder: GridHolder? = null
//
//        view = convertView
////        holder = view!!.tag as GridHolder
//        val provinceBean = provinceBeanList[position]
////        holder!!.text!!.text = provinceBeanList[position].name
////        holder.imageId.setImageResource(provinceBeanList[position].imgUrl)
//
//
////        holder.tv_name!!.text = provinceBean.name
//
//        //加载图片
////        Glide.with(context!!).load(provinceBean.imgUrl).into(holder!!.imageId!!)
//
//        return view
//    }
//
////
////    init {
////        layoutInflater = LayoutInflater.from(context)
////        this.context = context
////    }
//}