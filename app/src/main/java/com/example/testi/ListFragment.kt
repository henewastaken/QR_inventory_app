package com.example.testi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_list.view.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testi.data.ItemViewModel

/*
 Main menu fragment
 NYt käytetätään testaamiseen. Ei saa scannerista ppitivetttä vektoria,
 siinä on joku häitkkä, mut muutenkin nosql tai scv tulee kuitenkin käyttöön nii ei haittaa atm.
 */
class ListFragment : Fragment() {

    private lateinit var mItemVewModel: ItemViewModel

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Recyclerview
        val adapter = ListAdapter()
        val recyclerView = view.rwList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        // ItemViewModel
        mItemVewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        mItemVewModel.readAllData.observe(viewLifecycleOwner, { item ->
            adapter.setData(item)
        })



        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_insertFragment)
        }


        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_scan) {
            findNavController().navigate(R.id.action_listFragment_to_scanFragment)
        }
        if (item.itemId == R.id.menu_new_item) {
            findNavController().navigate(R.id.action_listFragment_to_insertFragment)
        }


        return super.onOptionsItemSelected(item)
    }


}