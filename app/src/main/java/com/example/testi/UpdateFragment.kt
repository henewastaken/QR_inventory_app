package com.example.testi

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
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

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mItemViewModel: ItemViewModel


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?)
    : View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mItemViewModel =ViewModelProvider(this).get(ItemViewModel::class.java)

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
        val amount = etUpdateAmount.text.toString().toDouble()
        val min = etUpdateMin.text.toString().toDouble()
        val optionalData = etUpdateOptionalData.text.toString()
        var updatedItem: Item // Item to be finally inserted (updated) to database


        if(inputCheck(name, etUpdateAmount.text, etUpdateMin.text)) {
            // Create updated item object
            if (TextUtils.isEmpty(optionalData)) {
                // Add "-" to optional data if it's empty
                updatedItem = Item(args.currentItem.id, name, amount, min, "-")
                 //item = Item(0, name, amount.toString().toDouble(), min.toString().toDouble(),"-")
            } else {
                 //item = Item(0, name, amount.toString().toDouble(), min.toString().toDouble(),optinalData)
                updatedItem = Item(args.currentItem.id, name, amount, min, optionalData)
            }

            // Update current item
            mItemViewModel.updateItem(updatedItem)
            Toast.makeText(requireContext(), "Succesfully updated", Toast.LENGTH_LONG).show()
            // Navigate back to list fragment
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        // All requred fields are not filled
        } else {
            Toast.makeText(requireContext(), "Please fill all required ields.", Toast.LENGTH_LONG).show()
        }
    }

    // Checks that name and amount is inputted
    // Returns false if any of name, amount or min is empty
    private fun inputCheck(name: String, amount: Editable, min: Editable): Boolean {
        return !(TextUtils.isEmpty(name) || amount.isEmpty() || min.isEmpty())
    }

}