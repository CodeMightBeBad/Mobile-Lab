package com.example.traveldiary.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.traveldiary.ui.screens.addTravel.AddTravelScreen
import com.example.traveldiary.ui.screens.addTravel.AddTravelViewModel
import com.example.traveldiary.ui.screens.home.HomeScreen
import com.example.traveldiary.ui.screens.settings.SettingsScreen
import com.example.traveldiary.ui.screens.settings.SettingsViewModel
import com.example.traveldiary.ui.screens.travelDetails.TravelDetailsScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

sealed interface NavigationRoute {
    @Serializable data object Home : NavigationRoute
    @Serializable data object AddTravel: NavigationRoute
    @Serializable data object Settings: NavigationRoute
    @Serializable data object TravelDetails: NavigationRoute
}

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navHostController,
        startDestination = NavigationRoute.Home
    ) {
        composable<NavigationRoute.Home> {
            HomeScreen(navHostController)
        }

        composable<NavigationRoute.AddTravel> {
            val addTravelViewModel = koinViewModel<AddTravelViewModel>()
            val state by addTravelViewModel.state.collectAsStateWithLifecycle()

            AddTravelScreen(
                state = state,
                actions = addTravelViewModel.actions,
                navController = navHostController
            )
        }

        composable<NavigationRoute.Settings> {
            val settingsViewModel = koinViewModel<SettingsViewModel>()

            SettingsScreen(
                username = settingsViewModel.username,
                onUsernameChange = settingsViewModel::updateUsername,
                navController = navHostController
            )
        }

        composable<NavigationRoute.TravelDetails> {
            TravelDetailsScreen(navHostController)
        }
    }
}