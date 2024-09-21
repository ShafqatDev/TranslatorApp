package com.example.translatorapp.domain.usecase

import com.example.translatorapp.domain.repository.HistoryRepository

class ToggleFavoriteUseCase(private val repository: HistoryRepository) {
    suspend operator fun invoke(id: Long) {
        repository.toggleFavorite(id)
    }
}
