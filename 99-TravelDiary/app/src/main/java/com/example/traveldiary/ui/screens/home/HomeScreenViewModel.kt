package com.example.traveldiary.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traveldiary.data.database.Travel
import com.example.traveldiary.data.repositories.TravelsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeScreenState(
    val travels: List<Travel>
)

class HomeScreenViewModel(repository: TravelsRepository) : ViewModel() {
    val state = repository.travels.map { HomeScreenState(it) }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = HomeScreenState(emptyList())
    )
}
