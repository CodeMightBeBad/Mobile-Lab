package com.example.traveldiary.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
