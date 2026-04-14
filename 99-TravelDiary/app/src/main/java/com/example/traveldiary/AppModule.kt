package com.example.traveldiary

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.traveldiary.data.database.TravelsDatabase
import com.example.traveldiary.data.repositories.SettingsRepository
import com.example.traveldiary.data.repositories.TravelsRepository
import com.example.traveldiary.ui.screens.addTravel.AddTravelViewModel
import com.example.traveldiary.ui.screens.home.HomeScreenViewModel
import com.example.traveldiary.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore("settings")

val appModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = TravelsDatabase::class.java,
            name = "travels"
        ).build()
    }

    single { get<Context>().dataStore }
    single { SettingsRepository(get()) }
    single { TravelsRepository(get<TravelsDatabase>().travelsDAO()) }

    viewModel { SettingsViewModel(get()) }
    viewModel { AddTravelViewModel(get()) }
    viewModel { HomeScreenViewModel(get()) }
}
