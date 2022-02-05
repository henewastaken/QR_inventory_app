package com.example.testi

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testi.data.Item
import com.example.testi.data.ItemViewModel
import kotlinx.android.synthetic.main.fragment_insert.*
import kotlinx.android.synthetic.main.fragment_insert.view.*
import java.lang.Exception


class InsertFragment : Fragment() {

    private lateinit var mItemViewModel: ItemViewModel

    lateinit var qrName: String // Data from the qr code



    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_insert, container, false)

        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)


        // Getting the qr name
        qrName = arguments?.getString("key").toString()
        Log.d("on_juttuset", qrName )


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
        if (item.itemId == R.id.menu_all_items ) {
            findNavController().navigate(R.id.action_scanFragment_to_allItemsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insetDataToDatabase() {
        // Get inserted text to values
        val name = etName.text.toString()
        val amount = etAmount.text
        val min = etMin.text
        val optionalData = etOptionalData.text.toString()
        var item: Item
        val errorCode = inputCheck(name, amount, min)

        if (errorCode == 0) {
            // if optionalData is empty, add line(-) to database
            if (TextUtils.isEmpty(optionalData)) {
                 item = Item(0, qrName, name, amount.toString().toDouble(), min.toString().toDouble(),"-")
            } else {
                 item = Item(0, qrName, name, amount.toString().toDouble(), min.toString().toDouble(), optionalData)
            }
            // Add data to database
            mItemViewModel.addItem(item)
            Toast.makeText(requireContext(), "Successfully inserted", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_insertFragment_to_listFragment)
        // Inputcheck returned false, toast depending on error code
        } else if (errorCode == 1) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
        } else if (errorCode == 2) {
            Toast.makeText(requireContext(), "Please check that numbers don't contain letters or comma.", Toast.LENGTH_SHORT).show()
        }

    }

    // Checks that name and amount is inputted
    // Returns int that is used as error code.
    // 0: all good
    // 1: any of name, amount or min is empty
    // 2: amount or min are in string format
    private fun inputCheck(name: String, amount: Editable, min: Editable): Int {
        if (!(TextUtils.isEmpty(name) || amount.isEmpty() || min.isEmpty())) {
            try {
                amount.toString().toDouble()
                min.toString().toDouble()
            } catch (e : Exception) {
                //Toast.makeText(requireContext(), "Insert amounts in number form", Toast.LENGTH_LONG).show()
                return 2 // Double error
            }
            return 0 // All good
        }
        return 1 // contains empty fields
    }
}