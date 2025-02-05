package com.funshow.nestedrecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var myAdapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**製作資料 */
        val data: List<MyData> = makeData()

        /**設置RecyclerView */
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //設置Adapter以及點擊回饋
        myAdapter = MyAdapter(data, object : MyAdapter.OnItemClick {
            override fun onItemClick(data: MyData.NestedData?, myData: MyData?) {
                if (myData != null) {
                    if (data != null) {
                        Toast.makeText(this@MainActivity, ("選擇了" + myData.title).toString() + "的 " + data.nesTitle , Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        recyclerView.adapter = myAdapter
    }

    private fun makeData(): List<MyData> {
        val data: MutableList<MyData> = ArrayList<MyData>()
        val title = arrayOf("週一", "週二", "週三", "週四", "週五")
        val nesTitle = arrayOf("清燉牛肉", "番茄炒蛋", "炸雞腿", "糖醋魚", "焗烤燉飯", "爆炒蝦仁", "紅燒鮭魚")
        for (i in title.indices) {
            val r = (Math.random() * 7).toInt()
            val nesArray: MutableList<MyData.NestedData> = ArrayList<MyData.NestedData>()
            for (j in 0 until r + 1) {
                nesArray.add(MyData.NestedData(nesTitle[(Math.random() * 7).toInt()]))
            }
            data.add(MyData(title[i], nesArray))
        }
        return data
    }
}