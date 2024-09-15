package com.example.translatorapp.presentation.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.translatorapp.presentation.main.HistoryCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen() {
    val viewModel: HistoryViewModel = koinViewModel()
    val state by viewModel.history.collectAsState()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.history) { history ->
            HistoryCard(message = history.message, translation = history.translation)
        }
    }
}