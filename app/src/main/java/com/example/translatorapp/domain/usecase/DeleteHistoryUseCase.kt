package com.example.translatorapp.domain.usecase

import com.example.translatorapp.domain.repository.HistoryRepository

class DeleteHistoryUseCase(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(id:Long) {
        historyRepository.deleteHistory(id)
    }
}