package com.example.testi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testi.data.ItemViewModel
import kotlinx.android.synthetic.main.fragment_all_items.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import android.util.Log

class AllItemsFragment : Fragment() {

    private lateinit var mItemVewModel: ItemViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_items, container, false)

        val adapter = ListAdapter(javaClass)
        val recyclerView = view.rw_all_items
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ItemViewModel
        mItemVewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        mItemVewModel.readAllData.observe(viewLifecycleOwner, { item ->
            adapter.setData(item)
        })

        // Navigate to insert fragment on floating action button (+) press
        view.fab_all_items.setOnClickListener{
            findNavController().navigate(R.id.action_allItemsFragment_to_insertFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

}