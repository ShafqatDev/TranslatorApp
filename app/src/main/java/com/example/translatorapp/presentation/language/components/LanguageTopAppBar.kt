package com.example.translatorapp.presentation.language.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@kotlin.OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageTopAppBar(
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack,
    text: String,
    searchValue: String = "",
    onValueChange: (String) -> Unit = {},
    onBackPress: () -> Unit = {},
    enableSearch: Boolean = true,
    isSearchActive: Boolean,
    onSearchToggle: (Boolean) -> Unit
) {
    TopAppBar(windowInsets = TopAppBarDefaults.windowInsets, modifier = modifier, navigationIcon = {
        IconButton(onClick = { onBackPress.invoke() }) {
            Icon(
                imageVector = icon, contentDescription = "Back", tint = Color.Black
            )
        }
    }, title = {
        if (isSearchActive) {
            TextField(
                value = searchValue,
                onValueChange = onValueChange,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(end = 10.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Black,
                    unfocusedIndicatorColor = Color.Black,
                ),
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 16.sp, color = Color.Black
                ),
                singleLine = true,
            )
        } else {
            TitleText(text = text)
        }
    }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = Color.White, titleContentColor = Color.Black
    ), actions = {
        if (enableSearch) {
            IconButton(onClick = {
                onSearchToggle(!isSearchActive)
                onValueChange("")
            }) {
                Icon(
                    imageVector = if (isSearchActive) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Black
                )
            }
        }
    })
}

@Composable
fun TitleText(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier, text = text, style = MaterialTheme.typography.titleLarge.copy(
            color = Color.Black, fontWeight = FontWeight.Bold
        )
    )
}