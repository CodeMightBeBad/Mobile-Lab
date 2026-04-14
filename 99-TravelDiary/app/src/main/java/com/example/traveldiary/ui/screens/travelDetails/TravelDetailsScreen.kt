package com.example.traveldiary.ui.screens.travelDetails

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traveldiary.ui.composables.AppBar
import java.lang.invoke.TypeDescriptor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelDetailsScreen(
    title: String,
    date: String,
    description: String,
    navController: NavHostController
) {
    val ctx = LocalContext.current

    fun share() {
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, title)
        }

        val shareIntent = Intent.createChooser(sendIntent, "Share travel")

        ctx.startActivity(shareIntent)
    }

    Scaffold(
        topBar = {
            AppBar(
                title = "Travel Details",
                navController = navController,
                showSearch = false
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = MaterialTheme.colorScheme.tertiary,
                onClick = ::share
            ) {
                Icon(Icons.Outlined.Share, "Share")
            }
        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(12.dp)
                .fillMaxSize()
        ) {
            Image(
                imageVector = Icons.Outlined.Image,
                contentDescription = "Travel image",
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary),
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(128.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(36.dp)
            )

            Text(title, style = MaterialTheme.typography.titleLarge)
            Text(date, style = MaterialTheme.typography.bodySmall)

            Spacer(Modifier.size(8.dp))

            Text(description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
