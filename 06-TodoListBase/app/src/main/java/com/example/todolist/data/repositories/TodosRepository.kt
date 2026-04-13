package com.example.todolist.data.repositories

import com.example.todolist.data.database.Todo
import com.example.todolist.data.database.TodosDAO

/*
 *  In this case the repository is redundant, we implement it anyway because in big applications
 *  some logic could be implemented here
*/
class TodosRepository(private val todosDAO: TodosDAO) {
    val todos = todosDAO.getAll()

    suspend fun upsert(todo: Todo) = todosDAO.upsert(todo)
    suspend fun delete(todo: Todo) = todosDAO.delete(todo)
}
