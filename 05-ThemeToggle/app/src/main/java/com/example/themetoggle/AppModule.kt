package com.example.themetoggle

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.themetoggle.data.repositories.ThemeRepository
import com.example.themetoggle.ui.screens.theme.ThemeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

// The datastore has to be created as an extension of context
val Context.dataStore by preferencesDataStore("theme")

val AppModule = module {
    // Creating generic singletons
    single { get<Context>().dataStore }
    single { ThemeRepository(get()) }

    // Creating the specific singleton for view models
    viewModel { ThemeViewModel(get()) }
}