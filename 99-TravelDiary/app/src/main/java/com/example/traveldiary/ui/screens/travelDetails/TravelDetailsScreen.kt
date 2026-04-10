package com.example.traveldiary.ui.screens.travelDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traveldiary.ui.RoundedImage
import com.example.traveldiary.ui.composables.AppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelDetailsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppBar(
                title = "Travel Details",
                navController = navController,
                showSearch = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {  }) {
                Icon(Icons.Outlined.Share, "Share")
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            Spacer(Modifier.size(20.dp))
            RoundedImage(size = 150.dp)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Destination",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "01/01/2026",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text("Description")
        }
    }
}