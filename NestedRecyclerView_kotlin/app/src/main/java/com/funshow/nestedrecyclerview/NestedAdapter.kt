package com.funshow.nestedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

internal class NestedAdapter(
    nestedData: List<MyData.NestedData>,
    private val parentPosition: Int,
    private val childClick: OnChildClick
) :
    RecyclerView.Adapter<NestedAdapter.NesHolder>() {
    private val nestedData: List<MyData.NestedData> = nestedData

    inner class NesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.textView_nesTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NesHolder {
        return NesHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_nested, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NesHolder, position: Int) {
        val item: MyData.NestedData = nestedData[position]
        holder.tvTitle.setText(item.nesTitle)
        holder.itemView.setOnClickListener { v: View? ->
            //取得點擊到的item，並使用interface回傳
            childClick.onChildClick(item, parentPosition)
        }
    }

    override fun getItemCount(): Int {
        return nestedData.size
    }

    internal interface OnChildClick {
        fun onChildClick(data: MyData.NestedData?, parentPosition: Int)
    }
}
