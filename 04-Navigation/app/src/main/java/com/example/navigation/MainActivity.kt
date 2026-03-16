package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}

sealed interface NavigationRoute {
    @Serializable data object ScreenOne : NavigationRoute
    @Serializable data object ScreenTwo : NavigationRoute
    @Serializable data object ScreenThree : NavigationRoute
}

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationRoute.ScreenOne
    ) {
        composable<NavigationRoute.ScreenOne> {
            ScreenOne(navHostController)
        }

        composable<NavigationRoute.ScreenTwo> {
            ScreenTwo(navHostController)
        }

        composable<NavigationRoute.ScreenThree> {
            ScreenThree(navHostController)
        }
    }
}
