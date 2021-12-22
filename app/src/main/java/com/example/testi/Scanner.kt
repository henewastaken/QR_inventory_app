package com.example.testi

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import java.util.*

/* Todennäkösesti turha luokka. Nyt testaamiseen  kyätetty
 */
class Scanner {

    val scanned = Vector<String>()


    fun insert (elem : String) {
        scanned.addElement(elem)
        Log.d("on_juttuset",scanned.size.toString())
    }

    fun getItems (): Vector<String> {
        Log.d("on_juttuset","size " + scanned.size.toString())
        return scanned
    }
}