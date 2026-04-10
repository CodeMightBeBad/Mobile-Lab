package com.example.traveldiary.ui.screens.addTravel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class AddTravelState(
    val destination: String = "",
    val date: String = "",
    val description: String = ""
)

data class AddTravelActions(
    val setDestination: (String) -> Unit,
    val setDate: (String) -> Unit,
    val setDescription: (String) -> Unit
)

class AddTravelViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddTravelState())
    val state = _state.asStateFlow()

    val actions = AddTravelActions(
        setDestination = { destination -> _state.update { it.copy(destination = destination) } },
        setDate = { date -> _state.update { it.copy(date = date) } },
        setDescription = { description -> _state.update { it.copy(description = description) } }
    )
}
