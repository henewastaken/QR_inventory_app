package com.example.testi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


// exportSchema is set to false, because it's not necessary for this application
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemDatabase: RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: ItemDatabase? = null

        // Checks if instance already exists and creates new instance if not existing
        // We want to use the same instance always
        fun getDatabase(context: Context): ItemDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ItemDatabase::class.java,
                        "item_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}