package com.example.roomdatabase


import androidx.room.Delete
import androidx.room.Update
import com.example.model.DataItems

interface PostDao {

    @Delete
    fun delete(post :DataItems)

    @Update
    fun update(post:DataItems)

    fun delete()
}