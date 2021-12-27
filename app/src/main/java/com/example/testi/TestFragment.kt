package com.example.testi

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset

/*
 Main menu fragment
 NYt käytetätään testaamiseen. Ei saa scannerista ppitivetttä vektoria,
 siinä on joku häitkkä, mut muutenkin nosql tai scv tulee kuitenkin käyttöön nii ei haittaa atm.
 */
class TestFragment : Fragment(R.layout.fragment_test) {
    private var scanner : Scanner? = Scanner()

    override fun onStart() {
        super.onStart()

        //Toast.makeText(activity, scanner?.getItems()?.size.toString(), Toast.LENGTH_SHORT).show()
        if (scanner?.getItems()?.isEmpty() == false) {
            //Toast.makeText(activity, scanner?.getItems()?.firstElement(), Toast.LENGTH_SHORT).show()


        }

    }
    override fun onPause() {
        super.onPause()
        Log.d("on_juttuset","on pause")
        //Toast.makeText(activity, "pause", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        readCsvFile()
        Log.d("on_juttuset","on resume, " + scanner?.getItems()?.size.toString())
        //Toast.makeText(activity, scanner?.getItems()?.firstElement(), Toast.LENGTH_SHORT).show()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("on_juttuset","save isntance")
    }
    fun readCsvFile() {
        Toast.makeText(activity, "tullaanko edes tänne?", Toast.LENGTH_SHORT).show()

        try {

            val fileName = (activity as MainActivity).getTestFileName()
            val inputStream  =  activity?.application?.applicationContext?.openFileInput(fileName)
            val text = inputStream?.bufferedReader().use { it?.readText() }
            text?.let { Log.d("on_juttuset", it) }
            val updatedText = view?.findViewById<TextView>(R.id.testFragmentText)
            updatedText?.setText(text)
        } catch (e : Exception) {
            Log.d("on_juttuset", "virhe " + e.toString())
        } finally {

        }

    }
}