package com.example.traveldiary.ui.screens.addTravel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traveldiary.ui.composables.AppBar
import com.example.traveldiary.ui.composables.RoundedImage
import com.example.traveldiary.ui.composables.TextBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTravelScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            AppBar(
                title = "Add Travel",
                navController = navController,
                showSearch = false,
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Filled.Check, "Confirm")
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            TextBox(
                label = "Destination",
                boxIcon = {
                    Icon(Icons.Filled.MyLocation, "Location")
                }
            )

            TextBox("Date")
            TextBox("Description")

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(50.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Icon(Icons.Outlined.CameraAlt, "Camera")
                    Text(
                        text = "Take a picture",
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }

            RoundedImage(150.dp)
        }
    }
}
