package com.example.model


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "posts")
class DataItems(
    @PrimaryKey(autoGenerate = true)
    var userId: Int, var id: Int, var title: String, var body: String
)