package com.example.testi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testi.data.Item
import kotlinx.android.synthetic.main.custom_row.view.*


/*
Recycler view adapter class
 */
class ListAdapter (val originClass: Class<Fragment>): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var itemList = emptyList<Item>()
    val TAG = "on_juttuset"
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return  MyViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.custom_row,
                         parent,
                        false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem: Item = itemList[position]

        Log.d(TAG, "class: " + originClass)
        Log.d(TAG, "ecualts: " + (originClass == AllItemsFragment::class.java).toString())

        // Set values from item to custom_row layout
        holder.itemView.tvNameList.text = currentItem.name
        holder.itemView.tvAmountList.text = currentItem.amount.toString()
        holder.itemView.tvMinList.text = currentItem.minTarget.toString()
        holder.itemView.tvOptinalList.text = currentItem.optionalData

        // Navigating to update fragment on list item click, and passing information
        holder.itemView.row_layout.setOnClickListener {

            // Navigation depending on which fragment got us here
            if (originClass == AllItemsFragment::class.java) {
                val action = AllItemsFragmentDirections.actionAllItemsFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
            if (originClass == ListFragment::class.java) {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    // Sets data of item to recycler view adapter
    fun setData(item: List<Item>) {
        this.itemList = item
        notifyDataSetChanged()
    }

    // Return amount of all items
    override fun getItemCount(): Int {
        return itemList.size
    }


}