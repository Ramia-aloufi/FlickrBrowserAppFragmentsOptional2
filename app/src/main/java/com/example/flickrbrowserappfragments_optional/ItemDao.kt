package com.example.flickrbrowserappfragments_optional

import android.provider.ContactsContract
import androidx.room.*

@Dao
interface ItemDao {

    @Query("SELECT * FROM flickerrr  ORDER BY text ASC")
    fun getAll(): List<Item1>

    @Insert
    fun insertItem(item: Item1)

    @Delete
    fun DeleteItem(item: Item1)

    @Update
    fun UpdateItem(item: Item1)




}