package com.example.testi

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.newSingleThreadContext


class InsertFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_insert, container, false)


        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_scan) {
            findNavController().navigate(R.id.action_insertFragment_to_scanFragment)
        }
        if (item.itemId == R.id.menu_main) {
            findNavController().navigate(R.id.action_insertFragment_to_listFragment)
        }

        return super.onOptionsItemSelected(item)
    }
}