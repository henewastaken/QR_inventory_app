package com.example.testi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_list.view.*
import androidx.navigation.fragment.findNavController

/*
 Main menu fragment
 NYt käytetätään testaamiseen. Ei saa scannerista ppitivetttä vektoria,
 siinä on joku häitkkä, mut muutenkin nosql tai scv tulee kuitenkin käyttöön nii ei haittaa atm.
 */
class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)


        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_insertFragment)
        }


        // Recyclerview stuff
        /*
        val adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer{ user ->
            adapter.setData(user)
        })


        // Navigate to add fragment when floating button is pressed
        view..setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        */
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_scan) {
            findNavController().navigate(R.id.action_listFragment_to_scanFragment)
        }
        if (item.itemId == R.id.menu_new_item) {
            findNavController().navigate(R.id.action_listFragment_to_insertFragment)
        }


        return super.onOptionsItemSelected(item)
    }
/*
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
        Log.d("on_juttuset","save instance")
    }
    fun readCsvFile() {
        Toast.makeText(activity, "tullaanko edes tänne?", Toast.LENGTH_SHORT).show()

        try {

            // Get the file name form MainActivity
            val fileName = (activity as MainActivity).getTestFileName()
            // Create inputsteam and open file
            val inputStream  =  activity?.application?.applicationContext?.openFileInput(fileName)
            // Read text to variable
            val text = inputStream?.bufferedReader().use { it?.readText() }
            text?.let { Log.d("on_juttuset", it) }
            // Set text to texView
            val updatedText = view?.findViewById<TextView>(R.id.testFragmentText)
            updatedText?.setText(text)
        } catch (e : Exception) {
            Log.d("on_juttuset", "virhe " + e.toString())
        } finally {

        }
    }
 */

}