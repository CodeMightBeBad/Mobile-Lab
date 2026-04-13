package com.example.todolist.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

// File for  Data Access Objects

@Dao
interface TodosDAO {
    @Query("SELECT * FROM todo")
    fun getAll(): Flow<List<Todo>>

    /*
     * If we insert an already existing object the function is going to update it, if the
     * object doesn't exist a new one is created
     */
    @Upsert
    suspend fun upsert(todo: Todo)

    @Delete
    suspend fun delete(todo: Todo)
}
