package com.example.testi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_list.view.*
import kotlinx.android.synthetic.main.fragment_scan.view.*
import java.io.FileOutputStream
import java.lang.Exception


class ScanFragment : Fragment() {

    internal lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createScanner()
        //startScanner()
        val view = inflater.inflate(R.layout.fragment_scan, container, false)

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
        if (item.itemId == R.id.menu_main) {
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
                    Toast.makeText(activity, result.contents, Toast.LENGTH_SHORT).show()
                    //scanner?.insert(result.contents)

                    try {
                        // Get filename from Main Activity
                        val fileName = (activity as MainActivity).getTestFileName()
                        // Create outputsream and write scanned to file
                        val output : FileOutputStream = activity?.application?.applicationContext?.openFileOutput(fileName, Context.MODE_APPEND)!!
                        output.write((result.contents + "/n").toByteArray())
                    }catch (e : Exception) {

                    }finally {

                    }



                }
            }
        }

    }

    // Start the QR Scanner
    fun startScanner() {
        val scanner = IntentIntegrator(activity)
        // QR Code Format
        scanner.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        // Set Text Prompt at Bottom of QR code Scanner Activity
        scanner.setPrompt("QR Code Scanner Prompt Text")
        // Start Scanner (don't use initiateScan() unless if you want to use OnActivityResult)
        mQrResultLauncher.launch(scanner.createScanIntent())
    }

}