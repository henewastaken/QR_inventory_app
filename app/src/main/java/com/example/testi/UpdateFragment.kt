package com.example.testi

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testi.data.Item
import com.example.testi.data.ItemViewModel
import kotlinx.android.synthetic.main.custom_row.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*
import java.lang.Exception

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mItemViewModel: ItemViewModel


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?)
    : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        Log.i("on_juttuset", "selected item " + args.currentItem.name)
        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.etUpdateName.setText(args.currentItem.name)
        view.etUpdateAmount.setText(args.currentItem.amount.toString())
        view.etUpdateMin.setText(args.currentItem.minTarget.toString())
        view.etUpdateOptionalData.setText(args.currentItem.optionalData)

        view.btnUpdate.setOnClickListener{
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val name = etUpdateName.text.toString()
        val amount = etUpdateAmount.text
        val min = etUpdateMin.text
        val optionalData = etUpdateOptionalData.text.toString()
        var updatedItem: Item // Item to be finally inserted (updated) to database
        val errorCode = inputCheck(name, amount, min)

        if (errorCode == 0) {
            // Create updated item object
            // if optionalData is empty, add line(-) to database
            if (TextUtils.isEmpty(optionalData)) {
                // Add "-" to optional data if it's empty
                updatedItem = Item(args.currentItem.id, args.currentItem.qrName, name, amount.toString().toDouble(), min.toString().toDouble(), "-")
            } else {
                updatedItem = Item(args.currentItem.id, args.currentItem.qrName, name, amount.toString().toDouble(), min.toString().toDouble(), optionalData)
            }

            // Update current item
            mItemViewModel.updateItem(updatedItem)
            Toast.makeText(requireContext(), "Succesfully updated", Toast.LENGTH_SHORT).show()
            // Navigate back to list fragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        // Inputcheck returned false, toast depending on error code
        } else if (errorCode == 1) {
            Toast.makeText(requireContext(), "Please fill all required fields", Toast.LENGTH_SHORT).show()
        } else if (errorCode == 2) {
            Toast.makeText(requireContext(), "Please check that numbers don't contain letters or comma.", Toast.LENGTH_LONG).show()
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