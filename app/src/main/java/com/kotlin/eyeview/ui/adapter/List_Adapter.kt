package com.kotlin.eyeview.ui.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.kotlin.eyeview.R


class List_Adapter(layoutResId: Int, data: List<Bean_mainList?>?) :
    BaseQuickAdapter<Bean_mainList?, BaseViewHolder>(
        layoutResId,
        data as MutableList<Bean_mainList?>?
    ){


    //绑定数据
    override fun convert(helper: BaseViewHolder, item: Bean_mainList?) {
        helper.setText(R.id.item_name, item?.name)
        Glide.with(context).load(item?.imgUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.eye)  //异常时显示的图片
            .placeholder(R.drawable.phone) //加载成功前显示的图片
            .fallback(R.drawable.backgrand) // url为空时显示的图片
//             .centerCrop()//圆
            .into(helper.getView<View>(R.id.item_img) as ImageView)

    }

    //点击事件
    override fun setOnItemClickListener(listener: OnItemClickListener?) {
        super.setOnItemClickListener(listener)
    }
//
//
//    //长按事件
//    override fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
//        super.setOnItemLongClickListener(listener)
//    }

//    //第一步 定义接口
//    interface OnItemClickListener {
//        fun onClick(position: Int)
//    }
//
//    private var listener: OnItemClickListener? = null
//
//    //第二步， 写一个公共的方法
//    fun setOnItemClickListener(listener: OnItemClickListener?) {
//        this.listener = listener
//    }
//
//
//    interface OnItemLongClickListener {
//        fun onClick(position: Int)
//    }
//
//    private var longClickListener: OnItemLongClickListener? = null
//
//    fun setOnItemLongClickListener(longClickListener: OnItemLongClickListener?) {
//        this.longClickListener = longClickListener
//    }




}