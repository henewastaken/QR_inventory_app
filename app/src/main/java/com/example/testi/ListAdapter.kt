package com.example.testi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
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
        lateinit var currentItem: Item
        currentItem = itemList[position]
       // Log.i("on_juttuset", "selected item " + currentItem.name)
        //holder.itemView.tvIdList.text = currentItem.id.toString()
        holder.itemView.tvNameList.text = currentItem.name
        holder.itemView.tvAmountList.text = currentItem.amount.toString()
        holder.itemView.tvMinList.text = currentItem.minTarget.toString()
        holder.itemView.tvOptinalList.text = currentItem.qrName

        // Navigating to update fragemnt on list item click, and passing information
        holder.itemView.row_layout.setOnClickListener {

            // TÄSSÄ SAATANASSA YHTÄKKIÄ JOKU VIKA
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

    }

    fun setData(item: List<Item>) {
        this.itemList = item
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}