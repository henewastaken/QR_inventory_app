package com.example.testi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
data class for item_table items.
id = primary key
name = name of item
amount = amount left inventiory
minTarget = amount of when user is notificated that item is running out
optionalData = any optional data user wants to input
 */

@Entity(tableName = "item_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val amount: Double,
    val minTarget: Double,
    val optionalData: String
)