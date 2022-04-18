package com.example.testi.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
viewModel class.
Communicates between repository and the UI
 */
class ItemViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Item>>
    val getAlarmData: LiveData<List<Item>>
    private val repository: ItemRepository

    init {
        val itemDao = ItemDatabase.getDatabase(application).itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllData
        getAlarmData = repository.getAlarm
    }



    fun addItem(item: Item) {
        // We want this to run in background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateItem(item)
        }
    }

    fun getItem (scanned: String): LiveData<Item> {
        return repository.getItem(scanned)
    }

}