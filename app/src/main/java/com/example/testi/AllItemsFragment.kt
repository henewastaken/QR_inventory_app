package com.example.testi

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testi.data.ItemViewModel
import kotlinx.android.synthetic.main.fragment_all_items.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import android.util.Log
import android.view.*

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

    // Inflate menubar
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bar, menu)
    }

    // Menubar selection handler and navigation
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_scan) {
            findNavController().navigate(R.id.action_allItemsFragment_to_scanFragment)
        }
        if (item.itemId == R.id.menu_new_item) {
            findNavController().navigate(R.id.action_allItemsFragment_to_insertFragment)
        }
        if (item.itemId == R.id.menu_list ) {
            findNavController().navigate(R.id.action_allItemsFragment_to_listFragment)
        }
        return super.onOptionsItemSelected(item)
    }
}