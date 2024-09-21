package com.example.translatorapp.domain.usecase

import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.repository.HistoryRepository

class InsertHistoryUseCase(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(history: HistoryEntity) {
        historyRepository.insertNote(history)
    }
}