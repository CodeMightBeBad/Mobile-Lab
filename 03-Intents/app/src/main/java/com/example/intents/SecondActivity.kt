package com.example.intents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.intents.ui.theme.IntentsTheme


class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsTheme {
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            val coordinates = "geo:44.1391, 12.24315"

                            MapButton(coordinates)
                            GoogleMapsButton(coordinates)
                            ShareTextButton(coordinates)
                        }
                    }
                }
            }
        }
    }
}

// Reusable button object to avoid repeating the same code for the three buttons
@Composable
fun MyButton(label: String, onclick: () -> Unit) {
    Button(
        modifier = Modifier.requiredSize(200.dp, 50.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        onClick = onclick
    ) {
        Text(label)
    }
}

@Composable
fun MapButton(coordinates: String) {
    val ctx = LocalContext.current

    MyButton(
        label = "Show Map",
        onclick = {
            val geolocation = coordinates.toUri()
            val intent = Intent(Intent.ACTION_VIEW, geolocation)

            if (intent.resolveActivity(ctx.packageManager) != null) {
                ctx.startActivity(intent)
            }
        }
    )
}

@Composable
fun GoogleMapsButton(coordinates: String) {
    val ctx = LocalContext.current

    MyButton(
        label = "Show Google Maps",
        onclick = {
            val geolocation = coordinates.toUri()
            val intent = Intent(Intent.ACTION_VIEW, geolocation).apply {
                `package` = "com.google.android.apps.maps"
            }

            if (intent.resolveActivity(ctx.packageManager) != null) {
                ctx.startActivity(intent)
            }
        }
    )
}

@Composable
fun ShareTextButton(coordinates: String) {
    val ctx = LocalContext.current

    MyButton(
        label = "Share as text",
        onclick = {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, coordinates)
                type = "text/plain"
            }

            val chooser = Intent.createChooser(intent, "Share via")

            if (chooser.resolveActivity(ctx.packageManager) != null) {
                ctx.startActivity(intent)
            }
        }
    )
}
