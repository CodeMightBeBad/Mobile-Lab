package com.example.themetoggle.ui.screens.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.themetoggle.data.models.Theme
import com.example.themetoggle.data.repositories.ThemeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

// Encapsulates the state of the class
data class ThemeState(
    val theme: Theme,
    val dynamicColor: Boolean
)

// Class used to implement the setters of the class
data class ThemeActions(
    val setTheme : (Theme) -> Unit,
    val setDynamicColor: (Boolean) -> Unit
)

class ThemeViewModel(repository: ThemeRepository) : ViewModel() {
    val state = combine(repository.theme, repository.dynamicColor) { theme, dynamicColor -> ThemeState(theme, dynamicColor) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ThemeState(Theme.System, true)
        )

    val actions = ThemeActions(
        setTheme = { theme -> viewModelScope.launch { repository.setTheme(theme) } },
        setDynamicColor = { dynamicColor -> viewModelScope.launch { repository.setDynamicColor(dynamicColor) } }
    )
}
