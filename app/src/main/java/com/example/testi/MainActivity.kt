package com.example.testi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val scanFragment = ScanFragment()
    private val testFragment = TestFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set custom toolbar as app bar
        setSupportActionBar(findViewById(R.id.toolbar))



        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, testFragment)
            .commit()

    }

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