package com.example.testi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

/*
 Main menu fragment
 NYt käytetätään testaamiseen. Ei saa scannerista ppitivetttä vektoria,
 siinä on joku häitkkä, mut muutenkin nosql tai scv tulee kuitenkin käyttöön nii ei haittaa atm.
 */
class TestFragment : Fragment(R.layout.fragment_test) {
    private var scanner : Scanner? = Scanner()

    override fun onStart() {
        super.onStart()

        Toast.makeText(activity, scanner?.getItems()?.size.toString(), Toast.LENGTH_SHORT).show()
        if (scanner?.getItems()?.isEmpty() == false) {
            Toast.makeText(activity, scanner?.getItems()?.firstElement(), Toast.LENGTH_SHORT).show()

        }

    }
    override fun onPause() {
        super.onPause()
        Log.d("on_juttuset","on pause")
        //Toast.makeText(activity, "pause", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()
        Log.d("on_juttuset","on resume, " + scanner?.getItems()?.size.toString())
        //Toast.makeText(activity, scanner?.getItems()?.firstElement(), Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("on_juttuset","save isntance")
    }
}