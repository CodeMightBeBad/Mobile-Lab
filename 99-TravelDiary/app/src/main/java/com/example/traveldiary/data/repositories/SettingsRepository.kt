package com.example.traveldiary.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map

class SettingsRepository(private val dataStore: DataStore<Preferences>) {
    companion object {
        private val USERNAME_KEY = stringPreferencesKey("USERNAME")
    }

    val username = dataStore.data.map { preferences ->
        preferences[USERNAME_KEY] ?: "username"
    }

    suspend fun setUsername(newUsername: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = newUsername
        }
    }
}