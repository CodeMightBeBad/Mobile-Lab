package com.example.traveldiary.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.traveldiary.NavigationRoute

@Composable
fun RoundedImage(size: Dp) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .size(size)
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

@Composable
fun TravelCard(text: String, navController: NavHostController) {
    Card (
        onClick = { navController.navigate(NavigationRoute.TravelDetailsScreen) },
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

@Composable
fun TextBox(
    label: String,
    value: String = "",
    boxIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = { Text(label) },
        trailingIcon = boxIcon,
        modifier = Modifier
            .fillMaxWidth()
    )
}
