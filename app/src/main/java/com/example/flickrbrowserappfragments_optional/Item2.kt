package com.example.flickrbrowserappfragments_optional


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Entity(tableName = "flickerrr")
data class Item1(
    @PrimaryKey(autoGenerate = true)@ColumnInfo(name = "id")  var id :Int?, // this is how can include id if needed
    @ColumnInfo(name = "img") val img: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "ischecked") var ischecked: Boolean = false)




