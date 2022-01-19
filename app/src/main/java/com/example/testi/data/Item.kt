package com.example.testi.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/*
data class for item_table items.
id = primary key
name = name of item
amount = amount left inventiory
minTarget = amount of when user is notificated that item is running out
optionalData = any optional data user wants to input
 */
@Parcelize
@Entity(tableName = "item_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val qrName: String,
    val name: String,
    val amount: Double,
    val minTarget: Double,
    val optionalData: String
): Parcelable