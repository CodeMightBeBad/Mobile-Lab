package com.example.traveldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.traveldiary.ui.screens.addTravel.AddTravelScreen
import com.example.traveldiary.ui.screens.home.HomeScreen
import com.example.traveldiary.ui.screens.settings.SettingsScreen
import com.example.traveldiary.ui.screens.travelDetails.TravelDetailsScreen
import com.example.traveldiary.ui.theme.TravelDiaryTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TravelDiaryTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}

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
