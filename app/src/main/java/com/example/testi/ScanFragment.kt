package com.example.testi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.testi.data.ItemViewModel
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.custom_row.view.*
import kotlinx.android.synthetic.main.fragment_scan.view.*


class ScanFragment : Fragment() {

    internal lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>
    private lateinit var mItemViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createScanner()
        //startScanner()
        val view = inflater.inflate(R.layout.fragment_scan, container, false)
        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        view.floatingActionButtonCamera.setOnClickListener {
            startScanner()
        }


        setHasOptionsMenu(true)
        return view
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_list) {
            findNavController().navigate(R.id.action_scanFragment_to_listFragment)
        }
        if (item.itemId == R.id.menu_new_item) {
            findNavController().navigate(R.id.action_scanFragment_to_insertFragment)
        }
        return super.onOptionsItemSelected(item)
    }


    fun createScanner() {

        mQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK) {
                val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)

                if(result.contents != null) {
                    // Do something with the contents (this is usually a URL)

                    //Toast.makeText(activity, result.contents, Toast.LENGTH_SHORT).show()

                    search(result.contents)

                    //Log.d("on_juttuset","moi")
                    //var item : Item =

                }
            }
        }
    }

    // Start the QR Scanner
    fun startScanner() {
        val scanner = IntentIntegrator(activity)
        // QR Code Format
        //scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("Scan qr code...")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())
    }

    private fun search (query: String) {
        val searchQuery = "%$query%"

        mItemViewModel.getItem(searchQuery).observe(viewLifecycleOwner, {item ->
            item.let {
                //Toast.makeText(activity, query, Toast.LENGTH_SHORT).show()
                // Jos item on uusi, siirrtyään insert fragmenttiin
                if (item == null) {
                    val bundle = Bundle()
                    bundle.putString("key", query)
                   // val action = ScanFragmentDirections.actionScanFragmentToInsertFragment(query)
                    findNavController().navigate(R.id.action_scanFragment_to_insertFragment, bundle)
                } else {
                    Toast.makeText(activity, query+ " exsists", Toast.LENGTH_SHORT).show()
                    Log.d("on_juttuset", item.qrName)

                    val action = ScanFragmentDirections.actionScanFragmentToUpdateFragment(item)
                    ListAdapter.MyViewHolder(requireView()).itemView.findNavController().navigate(action)
                }
            }
        })


//        // Recyclerview
//        val adapter = ListAdapter()
//        val recyclerView = view?.rwList
//        recyclerView?.adapter = adapter
//        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
//
//
//        // ItemViewModel
//        mItemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
//        mItemViewModel.readAllData.observe(viewLifecycleOwner, Observer {item ->
//
//        })
    }

}