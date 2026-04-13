package com.example.todolist.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/*
 *  The parameters in the decorator are: all the entities that represent the tables,
 *  and the db version
*/
@Database(entities = [Todo::class], version = 1)
abstract class TodoListDatabase : RoomDatabase() {
    abstract fun todosDAO() : TodosDAO
}