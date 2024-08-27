package com.example.translatorapp.presentation.language_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import com.example.translatorapp.data.data_source.model.LanguageModel
import com.example.translatorapp.data.data_source.model.languageList
import com.example.translatorapp.presentation.components.LocalNavController
import com.example.translatorapp.utils.LanguageTopAppBar

import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import org.koin.androidx.compose.koinViewModel

@Composable
fun LanguageScreen(
    viewModel: LanguageViewModel = koinViewModel()
) {

    val navController = LocalNavController.current
    val context = LocalContext.current
    val state = viewModel.state.collectAsState().value
    Scaffold(topBar = {
        LanguageTopAppBar(text = "Language",
            searchValue = state.searchField,
            enableSearch = true,
            onValueChange = {
                viewModel.onSearchFieldChange(it)
            },
            isSearchActive = state.isSearchActive,
            onBackPress = {
                navController.popBackStack()
            },
            onSearchToggle = {
                viewModel.onSearchToggle(it)
            })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            val filteredList = if (state.searchField.isEmpty()) {
                languageList
            } else {
                languageList.filter { it.name.contains(state.searchField, ignoreCase = true) }
            }
            LazyColumn {
                items(filteredList) {
                    ItemView(language = it, onClick = {
                        viewModel.saveLanguage(it)
                        Toast.makeText(context, it.shortCode, Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    })
                }
            }
        }

    }
}

@Composable
fun ItemView(language: LanguageModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.sdp)
            .padding(10.sdp)
            .clickable { onClick.invoke() }, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = language.name,
            fontSize = 18.ssp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
    Divider()
}