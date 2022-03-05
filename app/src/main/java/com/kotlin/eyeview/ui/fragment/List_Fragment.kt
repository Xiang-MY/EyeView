package com.kotlin.eyeview.ui.fragment

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.eyeview.R
import com.kotlin.eyeview.ui.activity.VideoActivity
import com.kotlin.eyeview.ui.adapter.Bean_mainList
import com.kotlin.eyeview.ui.adapter.List_Adapter
import com.kotlin.eyeview.ui.detection.activity.*
import kotlinx.android.synthetic.main.list__fragment.*
import org.jetbrains.anko.support.v4.runOnUiThread
import kotlin.concurrent.thread

class List_Fragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gridView: GridView
    private var adapter: List_Adapter? = null
    //眼保健操四节
    lateinit var recoveVideo1: LinearLayout
    lateinit var recoveVideo2: LinearLayout
    lateinit var recoveVideo3: LinearLayout
    lateinit var recoveVideo4: LinearLayout

    var list = ArrayList<Bean_mainList>()  //存放展示的图片和文字列表

    //网格布局
    // 图片资源
    //眼部测试按钮布局
    internal var imageIds = intArrayOf(
        R.drawable.semang, R.drawable.mingandu,
        R.drawable.sanguang, R.drawable.laohuayan, R.drawable.yali
    )

    //文字
    var textIds  = arrayOf("测色盲", "敏感度", "测散光", "老花眼", "测压力")

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

        //初始化视频封面及标题
        initList()

        val layoutManager = GridLayoutManager(context, 2)  //设置排布方式
        recyclerView.layoutManager = layoutManager
        adapter = List_Adapter(
            R.layout.list_item,
            list
        )//适配器

//        recyclerview的点击事件(护眼小知识下点击播放视频
//        adapter!!.setOnItemClickListener { adapter, view, position ->
////            val position = recyclerView.getChildAdapterPosition(view)
//            Log.d(TAG, "onViewCreated: $position")
//            val intent = Intent(activity,VideoActivity::class.java)
//            startActivity(intent)
//        }
//       adapter!!.setOnItemClickListener { adapter, view, position ->
//           Log.d(TAG, "onViewCreated: $position")
//       }



        //recyclerview的长按事件
//        adapter!!.setOnItemLongClickListener { adapter, view, position ->
//
//        }


        recyclerView.adapter = adapter
        adapter!!.setOnItemClickListener { adapter, view, position ->
//            val position = recyclerView.getChildAdapterPosition(view)
//            Toast.makeText(context, position.toString(), Toast.LENGTH_SHORT).show();

            val intent = Intent(activity,VideoActivity::class.java)
            intent.putExtra("position",position.toString())
            startActivity(intent)
        }




        //添加头部（恢复训练
        val headerView = LayoutInflater.from(context).inflate(R.layout.list_header, null)
        adapter!!.addHeaderView(headerView)

        // 创建一个List对象，List对象的元素是Map
        val listItems = ArrayList<Map<String, Any>>()
        for (i in imageIds.indices)
        {
            val listItem = HashMap<String, Any>()
            listItem["image"] = imageIds[i]
            listItem["text"] = textIds[i]
            listItems.add(listItem)
            Log.d(TAG, "onViewCreated: listItems = $listItems")
        }
        // 创建一个SimpleAdapter传入GridView中的图片和文字
        val simpleAdapter = SimpleAdapter(
            context, listItems, R.layout.grid_item,
            arrayOf("image", "text"), intArrayOf(R.id.grid_img, R.id.grid_name)
        ) // 使用/layout/cell.xml文件作为界面布局

        gridView = headerView.findViewById(R.id.grid)
        gridView.adapter = simpleAdapter
        gridView.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            )
            {
                // 显示当前被选中的图片
//                view.grid_img.setImageResource(imageIds[position])
//                view.grid_name.text = textIds[position]
//                Log.d(TAG, "onItemSelected: textt = ${view.grid_name.text}")
            }
            override fun onNothingSelected(parent: AdapterView<*>)
            {
            }
        }
        //gridView点击事件
        gridView.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                toast("点击了第${position}项,值为${imageIds[position]}")
                //跳转至不同测试的界面
                if(position==0){
                    val intent = Intent(activity, QuestionActivity::class.java)
                    startActivity(intent)

                }else if (position==1){
                    val intent = Intent(activity, SensitivityActivity::class.java)
                    startActivity(intent)
                }else if(position==2){
                    val intent=Intent(activity, AstigmatismActivity::class.java)
                    startActivity(intent)
                }else if(position==3){
                    val intent=Intent(activity, PresbyopiaActivity::class.java)
                    startActivity(intent)
                }else if(position==4){
                    val intent=Intent(activity, PressureActivity::class.java)
                    startActivity(intent)
                }

            }
        }

        //点击第几节跳转播放视频activity同时通过putExtra传递第几节的信息
        //恢复训练下的分节视频
        recoveVideo1 = headerView.findViewById(R.id.video1)
        recoveVideo1.setOnClickListener {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("data", "1")
            startActivity(intent)
        }
        recoveVideo2 = headerView.findViewById(R.id.video2)
        recoveVideo2.setOnClickListener {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("data", "2")
            startActivity(intent)
        }
        recoveVideo3 = headerView.findViewById(R.id.video3)
        recoveVideo3.setOnClickListener {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("data", "3")
            startActivity(intent)
        }
        recoveVideo4 = headerView.findViewById(R.id.video4)
        recoveVideo4.setOnClickListener {
            val intent = Intent(activity, VideoActivity::class.java)
            intent.putExtra("data", "4")
            startActivity(intent)
        }


        //下拉刷新
        swipeRefresh.setOnRefreshListener {
            Toast.makeText(context, "下拉刷新", Toast.LENGTH_SHORT).show()
            refresh(recyclerView.adapter as List_Adapter)
        }

        //上滑刷新
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItem =
                    (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                val totalItemCount: Int = layoutManager.itemCount //总条目
                //lastVisibleItem >= totalItemCount - 5 表示剩下5个item实现预加载
                // dy>0 表示向下滑动,滑动距离
                if (totalItemCount >= 2 && lastVisibleItem >= totalItemCount - 2 && dy > 0) {
//                    Toast.makeText(context,"上滑刷新",Toast.LENGTH_SHORT).show()
                    refresh(recyclerView.adapter as List_Adapter)
                }
            }
        })

    }

    //添加列表数据
    private fun initList(){
        val item1 = Bean_mainList(
            "十大悖论之色盲悖论",
            R.drawable.semangbeilun
//            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2676935521,922112450&fm=11&gp=0.jpg"
        )
        val item2 = Bean_mainList(
            "科普知识：色盲眼中的世界",
            R.drawable.semangshijie
//            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3824886304,665215047&fm=26&gp=0.jpg"
        )
        val item3 = Bean_mainList(
            "科普知识：近视和散光有什么不同",
            R.drawable.sanguangkepu
//            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3824886304,665215047&fm=26&gp=0.jpg"
        )
        val item4 = Bean_mainList(
            "科学用眼，关注健康",
            R.drawable.kexueyongyan
//            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3824886304,665215047&fm=26&gp=0.jpg"
        )
        list.add(item1)
        list.add(item4)
        list.add(item2)
        list.add(item3)

    }

    //刷新
    private fun refresh(adapter: List_Adapter) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initList()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            } }
    }

}