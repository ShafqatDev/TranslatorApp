package com.example.translatorapp.domain.usecase

import com.example.translatorapp.domain.repository.HistoryRepository

class UpdateFavHistoryUseCase(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(id: Long, isFavorite: Boolean) {
        historyRepository.updateFavoriteStatus(id, isFavorite)
    }
}