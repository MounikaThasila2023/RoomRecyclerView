package com.example.roomdatabase


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.model.DataItems

@Database(entities = [DataItems::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun postDao():PostDao
}