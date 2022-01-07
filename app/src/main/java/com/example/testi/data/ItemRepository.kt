package com.example.testi.data

import androidx.lifecycle.LiveData


class ItemRepository (private val itemDao: ItemDao){

    val readAllData: LiveData<List<Item>> = itemDao.readAllItems()

    // Adds item
    suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    // Updates item
    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }
}