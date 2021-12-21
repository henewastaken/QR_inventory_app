package com.example.testi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

class NewItem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        // Set custom toolbar as app bar
        setSupportActionBar(findViewById(R.id.toolbar))

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    // Add functionality to menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_main -> {
                this.startActivity(Intent(this, MainActivity::class.java))
                return true
            }
            R.id.menu_new_item -> Toast.makeText(this, "new item", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
}