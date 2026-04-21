package com.example.gps

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gps.data.LocationService
import com.example.gps.ui.LocationDisabledAlert
import com.example.gps.ui.PermissionDeniedAlert
import com.example.gps.ui.PermissionPermanentlyDeniedSnackbar
import com.example.gps.ui.theme.GPSTheme
import com.example.gps.utils.PermissionStatus
import com.example.gps.utils.rememberMultiplePermissions
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GPSTheme {
                GPSScreen()
            }
        }
    }
}

@Composable
fun GPSScreen() {
    var showLocationDisabledAlert by remember { mutableStateOf(false) }
    var showPermissionDeniedAlert by remember { mutableStateOf(false) }
    var showPermissionPermanentlyDeniedSnackbar by remember { mutableStateOf(false) }

    val ctx = LocalContext.current
    val locationService = remember { LocationService(ctx) }

    val coordinates by locationService.coordinates.collectAsStateWithLifecycle()
    val isLoading by locationService.isLoading.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()
    fun getCurrentLocation() = scope.launch {
        try {
            locationService.getCurrentLocation()
        } catch (_: IllegalStateException) {
            showLocationDisabledAlert = true
        }
    }

    val locationPermissions = rememberMultiplePermissions(
        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    ) { statuses ->
        when {
            statuses.any { it.value == PermissionStatus.Granted } -> getCurrentLocation()
            statuses.all { it.value == PermissionStatus.PermanentlyDenied } -> showPermissionPermanentlyDeniedSnackbar = true
            else -> showPermissionDeniedAlert = false
        }
    }

    fun getLocationOrRequestPermission() {
        if (locationPermissions.statuses.any { it.value.isGranted }) getCurrentLocation()
        else locationPermissions.launchPermissionRequest()
    }

    fun openLocationSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }

        if (intent.resolveActivity(ctx.packageManager) != null) {
            ctx.startActivity(intent)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        if (isLoading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LocationDisabledAlert(
                show = showLocationDisabledAlert,
                onAction = ::openLocationSettings,
                onHide = { showLocationDisabledAlert = false }
            )

            PermissionDeniedAlert(
                show = showPermissionDeniedAlert,
                onAction = locationPermissions::launchPermissionRequest,
                onHide = { showPermissionDeniedAlert = false }
            )

            Button(onClick = ::getLocationOrRequestPermission) {
                Text("Get current location")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Latitude: ${coordinates?.latitude ?: "-"}")
            Text("Longitude: ${coordinates?.longitude ?: "-"}")
        }

        PermissionPermanentlyDeniedSnackbar(
            snackbarHostState = snackBarHostState,
            show = showPermissionPermanentlyDeniedSnackbar,
            onAction = {
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    data = Uri.fromParts("package", ctx.packageName, null)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }

                if (intent.resolveActivity(ctx.packageManager) != null) ctx.startActivity(intent)
            },
            onHide = { showPermissionPermanentlyDeniedSnackbar = false }
        )
    }
}
