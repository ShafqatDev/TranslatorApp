package com.example.translatorapp.presentation.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.usecase.GetHistoryUseCase
import com.example.translatorapp.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HistoryState(
    val history: List<HistoryEntity> = emptyList()
)

class HistoryViewModel(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state = _state.asStateFlow()

    init {
        getNotes()
    }

    private fun getNotes() {
        viewModelScope.launch {
            getHistoryUseCase().collect { notes ->
                _state.update { it.copy(history = notes) }
            }
        }
    }

    fun toggleFavorite(id: Long) {
        viewModelScope.launch {
            toggleFavoriteUseCase.invoke(id)
        }
    }
}