package com.example.themetoggle.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.themetoggle.data.models.Theme
import kotlinx.coroutines.flow.map

class ThemeRepository (private val dataStore: DataStore<Preferences>) {
    // Type of container for all static values of the class
    companion object {
        /*
        *   When we use THEME_KEY in the data store we get a string,
        *   the value in the string has to be unique
        */
        private val THEME_KEY = stringPreferencesKey("THEME")
        private val DYNAMIC_COLOR = booleanPreferencesKey("DYNAMIC_COLOR")
    }

    /*
     *  The .map lambda is called everytime the values in the data stores change,
     *  the ?: operator is used to set a default value if the value is null
    */
    val dynamicColor = dataStore.data.map { preferences -> preferences[DYNAMIC_COLOR] ?: true }

    // We use suspend because all calls to data stores are asynchronous
    suspend fun setDynamicColor(enabled: Boolean) {
        dataStore.edit { preferences -> preferences[DYNAMIC_COLOR] = enabled }
    }

    // If the conversion fails use the system theme as fallback
    val theme = dataStore.data.map { preferences ->
        try {
            Theme.valueOf(preferences[THEME_KEY] ?: "System")
        } catch (_: Exception) {
            Theme.System
        }
    }

    suspend fun setTheme(theme: Theme) {
        dataStore.edit { preferences -> preferences[THEME_KEY] = theme.toString() }
    }
}