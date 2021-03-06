package com.example.testi.data

import androidx.lifecycle.LiveData


class ItemRepository (private val itemDao: ItemDao){

    val readAllData: LiveData<List<Item>> = itemDao.readAllItems()
    val getAlarm: LiveData<List<Item>> = itemDao.getAlarm()

    // Adds item
    suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    // Updates item
    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

    fun getItem (scanned: String): LiveData<Item> {
        return itemDao.getItem(scanned)
    }


}