package com.example.testi

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.testi.data.Item
import com.example.testi.data.ItemViewModel
import kotlinx.android.synthetic.main.fragment_insert.*
import kotlinx.android.synthetic.main.fragment_insert.view.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.coroutines.newSingleThreadContext


class InsertFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_insert, container, false)

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.btnAdd.setOnClickListener{
            insetDataToDatabase()
        }

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
        if (item.itemId == R.id.menu_list) {
            findNavController().navigate(R.id.action_insertFragment_to_listFragment)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insetDataToDatabase() {
        // Get inserted text to values
        val name = etName.text.toString()
        val amount = etAmount.text
        val min = etMin.text
        val optinalData = etOptionalData.text.toString()
        var item: Item

        if (inputCheck(name, amount, min)) {
            // if optinalData is empty, add line(-) to database
            if (TextUtils.isEmpty(optinalData)) {
                 item = Item(0, name, amount.toString().toDouble(), min.toString().toDouble(),"-")
            } else {
                 item = Item(0, name, amount.toString().toDouble(), min.toString().toDouble(),optinalData)
            }
            // Add data to database
            mItemViewModel.addItem(item)
            Toast.makeText(requireContext(), "Succesfully inserted", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_insertFragment_to_listFragment)
        // Inputcheck returned false, toast is input correct?
        } else {
            Toast.makeText(requireContext(), "Please fill in all fields.", Toast.LENGTH_LONG).show()
        }

    }

    // Checks that name and amount is inputted
    // Returns false if any of name, amount or min is empty
    private fun inputCheck(name: String, amount: Editable, min: Editable): Boolean {
        return !(TextUtils.isEmpty(name) || amount.isEmpty() || min.isEmpty())
    }
}