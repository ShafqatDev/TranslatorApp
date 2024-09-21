package com.example.translatorapp.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.usecase.GetHistoryUseCase
import com.example.translatorapp.domain.usecase.ToggleFavoriteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class FavState(
    val favHistory: List<HistoryEntity> = emptyList()
)

class FavViewModel(
    private val getHistoryUseCase: GetHistoryUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavState())
    val state = _state.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() {
        viewModelScope.launch {
            getHistoryUseCase().collect { notes ->
                _state.update { currentState -> currentState.copy(favHistory = notes.filter { it.isFavorite }) }
            }
        }
    }

    fun toggleFavorite(id: Long) {
        viewModelScope.launch {
            toggleFavoriteUseCase(id)
        }
    }
}
