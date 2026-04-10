package com.example.traveldiary

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.traveldiary.data.repositories.SettingsRepository
import org.koin.dsl.module

val Context.dataStore by preferencesDataStore("settings")

val appModule = module {
    single { get<Context>().dataStore }
    single { SettingsRepository(get()) }
}
