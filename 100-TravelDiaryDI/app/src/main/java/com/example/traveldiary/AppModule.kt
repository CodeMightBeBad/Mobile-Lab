package com.example.traveldiary

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.traveldiary.data.database.TripsDatabase
import com.example.traveldiary.data.repositories.SettingsRepository
import com.example.traveldiary.data.repositories.TripsRepository
import com.example.traveldiary.ui.screens.addtravel.AddTravelViewModel
import com.example.traveldiary.ui.screens.home.HomeViewModel
import com.example.traveldiary.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore("settings")

val appModule = module {
    single { get<Context>().dataStore }

    single { SettingsRepository(get()) }

    single {
        Room.databaseBuilder(
            context = get(),
            klass = TripsDatabase::class.java,
            name = "trips"
        ).build()
    }

    single { TripsRepository(get<TripsDatabase>().tripsDAO()) }

    viewModel { HomeViewModel(get()) }

    viewModel { AddTravelViewModel() }

    viewModel { SettingsViewModel(get()) }
}
