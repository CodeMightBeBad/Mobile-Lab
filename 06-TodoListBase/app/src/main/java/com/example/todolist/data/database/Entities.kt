package com.example.todolist.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// The decorator entity is used to specify that this data class is a database table
@Entity
data class Todo(
    // Decorators inside the data class are used to specify what kind of data is each table column
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val content: String,
    @ColumnInfo val completed: Boolean = false
)
