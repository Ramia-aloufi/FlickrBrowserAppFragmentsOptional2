package com.example.flickrbrowserappfragments_optional

import android.content.Context
import android.provider.ContactsContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [Item1::class],version = 1,exportSchema = false)
abstract class ItemDatabase:RoomDatabase() {

    companion object{
        var instance:ItemDatabase?=null;
        fun getInstance(ctx: Context):ItemDatabase
        {
            if(instance!=null)
            {
                return  instance as ItemDatabase;
            }
            instance= Room.databaseBuilder(ctx,ItemDatabase::class.java,"ram").run { allowMainThreadQueries() }.build();
            return instance as ItemDatabase;
        }
    }
    abstract fun ItemDao(): ItemDao;
}