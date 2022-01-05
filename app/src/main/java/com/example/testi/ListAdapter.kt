package com.example.testi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testi.data.Item
import kotlinx.android.synthetic.main.custom_row.view.*


/*
Recycler view adapter class
 */
class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var itemList = emptyList<Item>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return  MyViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.custom_row,
                         parent,
                        false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.itemView.tvIdList.text = currentItem.id.toString()
        holder.itemView.tvNameList.text = currentItem.name
        holder.itemView.tvAmountList.text = currentItem.amount.toString()
        holder.itemView.tvMinList.text = currentItem.minTarget.toString()
        holder.itemView.tvOptinalList.text = currentItem.optionalData

    }

    fun setData(item: List<Item>) {
        this.itemList = item
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}