package com.example.translatorapp.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translatorapp.domain.usecase.TranslateTextUseCase
import com.example.translatorapp.data.controller.SharedPreferences
import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.usecase.GetHistoryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HistoryState(
    val history: List<HistoryEntity> = emptyList()
)

class HistoryViewModel(
    private val getHistoryUseCase: GetHistoryUseCase
) : ViewModel() {

    private val _history = MutableStateFlow(HistoryState())
    val history = _history.asStateFlow()

    init {
        getHistory()
    }

    private fun getHistory() {
        viewModelScope.launch {
            getHistoryUseCase.invoke().collect { history ->
                _history.update { it.copy(history = history) }
            }
        }
    }
}
