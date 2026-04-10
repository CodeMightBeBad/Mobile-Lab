package com.example.traveldiary.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.traveldiary.ui.screens.addTravel.AddTravelScreen
import com.example.traveldiary.ui.screens.home.HomeScreen
import com.example.traveldiary.ui.screens.settings.SettingsScreen
import com.example.traveldiary.ui.screens.travelDetails.TravelDetailsScreen
import kotlinx.serialization.Serializable

sealed interface NavigationRoute {
    @Serializable data object Homescreen : NavigationRoute
    @Serializable data object AddTravelScreen: NavigationRoute
    @Serializable data object SettingsScreen: NavigationRoute
    @Serializable data object TravelDetailsScreen: NavigationRoute
}

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navHostController,
        startDestination = NavigationRoute.Homescreen
    ) {
        composable<NavigationRoute.Homescreen> {
            HomeScreen(navHostController)
        }

        composable<NavigationRoute.AddTravelScreen> {
            AddTravelScreen(navHostController)
        }

        composable<NavigationRoute.SettingsScreen> {
            SettingsScreen(navHostController)
        }

        composable<NavigationRoute.TravelDetailsScreen> {
            TravelDetailsScreen(navHostController)
        }
    }
}