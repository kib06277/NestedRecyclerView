package com.funshow.nestedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool

class MyAdapter(
    myData: List<MyData> ,
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(), NestedAdapter.OnChildClick {
    private val viewPool = RecycledViewPool()
    private val myData: List<MyData> = myData

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val tvTitle: TextView = itemView.findViewById(R.id.textView_Title)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerview_Nested)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.setText(myData[position].title)
        //設置巢狀 RecyclerView
        holder.recyclerView.adapter = NestedAdapter(
            myData[position].nesData ,
            position, this
        )
        holder.recyclerView.setRecycledViewPool(viewPool)
    }

    override fun getItemCount(): Int {
        return myData.size
    }

    /**此處為點選Child-Item後從
     * @see NestedAdapter 的回傳
     */
    override fun onChildClick(data: MyData.NestedData?, parentPosition: Int) {
        onItemClick.onItemClick(data, myData[parentPosition])
    }

    interface OnItemClick {
        fun onItemClick(data: MyData.NestedData?, myData: MyData?)
    }
}