package com.example.themetoggle

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.themetoggle.data.repositories.ThemeRepository
import com.example.themetoggle.ui.screens.theme.ThemeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore("theme")

val AppModule = module {
    single { get<Context>().dataStore }
    single { ThemeRepository(get()) }
    viewModel { ThemeViewModel(get()) }
}