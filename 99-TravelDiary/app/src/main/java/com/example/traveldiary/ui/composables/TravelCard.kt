package com.example.traveldiary.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traveldiary.ui.NavigationRoute

@Composable
fun TravelCard(text: String, navController: NavHostController) {
    Card (
        onClick = { navController.navigate(NavigationRoute.TravelDetails) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .padding(5.dp)
            .size(width = 190.dp, height = 190.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        // Use a Column object as container to center the card content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            RoundedImage(100.dp)

            Spacer(Modifier.size(16.dp))

            Text(
                text,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
