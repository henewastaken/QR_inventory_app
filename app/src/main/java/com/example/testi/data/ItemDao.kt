package com.example.testi.data

import androidx.lifecycle.LiveData
import androidx.room.*

/*
Data access object interface for item_table
 */


@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

     @Update
     suspend fun updateItem(item: Item)

    @Query("SELECT * FROM item_table ORDER BY id ASC" )
    fun readAllItems(): LiveData<List<Item>>
}