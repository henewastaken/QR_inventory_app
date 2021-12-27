package com.example.testi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception


class ScanFragment : Fragment(R.layout.fragment_scan) {
    private var scanner : Scanner? = Scanner()
    internal lateinit var mQrResultLauncher : ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createScanner()
        startScanner()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        Log.d("on_juttuset", "scan fragment ON resume")
    }


    fun createScanner() {

        mQrResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if(it.resultCode == Activity.RESULT_OK) {
                val result = IntentIntegrator.parseActivityResult(it.resultCode, it.data)

                if(result.contents != null) {
                    // Do something with the contents (this is usually a URL)
                    Toast.makeText(activity, result.contents, Toast.LENGTH_SHORT).show()
                    scanner?.insert(result.contents)

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