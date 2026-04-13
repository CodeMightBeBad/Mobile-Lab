package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.data.database.Todo
import com.example.todolist.data.repositories.TodosRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TodosState(val todos: List<Todo>)

data class TodosActions(
    val addTodo: (Todo) -> Unit,
    val removeTodo: (Todo) -> Unit,
    val toggleComplete: (Todo) -> Unit
)

class TodosViewModel(repository: TodosRepository) : ViewModel() {
    val state = repository.todos.map { TodosState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = TodosState(emptyList())
    )

    val actions = TodosActions(
        addTodo = { todo -> viewModelScope.launch { repository.upsert(todo) }},
        removeTodo = { todo -> viewModelScope.launch { repository.delete(todo) }},
        toggleComplete = { todo -> viewModelScope.launch {
          repository.upsert(todo.copy(completed = !todo.completed))
        }}
    )
}
