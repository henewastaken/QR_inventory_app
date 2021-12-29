package com.example.testi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    val fileName = "testfile"
    private val scanFragment = ScanFragment()
    private val testFragment = ListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Create the file
        // val fileName = getTestFileName()
        // val output : FileOutputStream = openFileOutput(fileName, Context.MODE_APPEND)!!
        // Set custom toolbar as app bar
        //val toolbar = setSupportActionBar(findViewById(R.id.toolbar))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setupActionBarWithNavController(navController)
        //setupActionBarWithNavController(findNavController(R.id.fragment))

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
    // Function returns the file name. Can be used in fragemnts also
    fun getTestFileName(): String {
        return fileName
    }
}



/*
    // inflate menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    // Add functionality to menu items

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_new_item -> {
                this.startActivity(Intent(this, NewItem::class.java))
                return true
            }
            R.id.menu_scan -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, scanFragment)
                    .addToBackStack(null)
                    .commit()


        }

            R.id.menu_main -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, testFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

 */