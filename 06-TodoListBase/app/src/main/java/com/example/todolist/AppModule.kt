package com.example.todolist

import androidx.room.Room
import com.example.todolist.data.database.TodoListDatabase
import com.example.todolist.data.repositories.TodosRepository
import com.example.todolist.ui.TodosViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = TodoListDatabase::class.java,
            name = "todo-list"
        ).build()
    }

    // We do this to avoid runtime errors cause by Koin not being able to find the DAO
    single { TodosRepository(get<TodoListDatabase>().todosDAO()) }

    viewModel { TodosViewModel(get()) }
}
