package com.example.themetoggle

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/*
*   This file is needed to start the Dependency Injection of Koin globally for the application.
*   To do this we create a ThemeToggleApplication class that inherits from application, and we
*   override the onCreate method to start koin. This class has to added to the application manifest
*   file
*/

class ThemeToggleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ThemeToggleApplication)
            modules(AppModule)
        }
    }
}