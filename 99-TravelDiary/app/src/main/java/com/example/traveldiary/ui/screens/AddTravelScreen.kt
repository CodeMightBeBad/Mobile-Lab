package com.example.traveldiary.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class AddTravelScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Scaffold(
                topBar = { AddTravelTopBar() },
                floatingActionButton = {
                    FloatingActionButton(onClick = { }) {
                        Icon(Icons.Filled.Check, "Confirm")
                    }
                }
            ) { innerPadding ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth()
                ) {
                    LocationTextField("Destination")
                    TravelTextField("Date")
                    TravelTextField("Description")

                    Spacer(Modifier.size(20.dp))

                    PictureButton()

                    Spacer(Modifier.size(20.dp))

                    ImageIcon()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTravelTopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = { Text("Add Travel") },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(Icons.Outlined.Settings, "Settings")
            }
        }
    )
}

@Composable
fun TravelTextField(placeholder: String) {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text(placeholder) },
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .fillMaxWidth()
    )
}

@Composable
fun LocationTextField(placeholder: String) {
    OutlinedTextField(
        value = "",
        onValueChange = { },
        label = { Text(placeholder) },
        modifier = Modifier
            .padding(10.dp, 5.dp)
            .fillMaxWidth(),
        trailingIcon = { Icon(Icons.Filled.MyLocation, "My location") }
    )
}

@Composable
fun PictureButton() {
    Button (
        modifier = Modifier
            .requiredSize(200.dp, 50.dp)
            .clip(CircleShape),
        onClick = { }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .requiredSize(130.dp, 30.dp)
                .padding(5.dp)
        ) {
            Icon(Icons.Outlined.PhotoCamera, "Camera")
            Spacer(Modifier.size(10.dp))
            Text("Take a picture")
        }
    }
}

@Composable
fun ImageIcon() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
    ) {
        // Image of the card
        Icon(
            imageVector = Icons.Outlined.Image,
            contentDescription = "Travel Image",
            modifier = Modifier
                .fillMaxSize(),
            tint = Color.White
        )
    }
}
