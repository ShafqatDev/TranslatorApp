package com.example.translatorapp.presentation.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.example.translatorapp.presentation.history.components.HistoryItem
import com.example.translatorapp.ui.theme.MainColor
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen(
) {
    val viewModel: HistoryViewModel = koinViewModel()
    val state = viewModel.state.collectAsState().value

    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MainColor)
    ) {
        items(state.history) { note ->
            HistoryItem(note = note, onToggleFavorite = viewModel::toggleFavorite)
        }
    }
}
