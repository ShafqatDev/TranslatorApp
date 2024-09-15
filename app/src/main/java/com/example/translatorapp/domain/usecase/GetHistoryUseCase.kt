package com.example.translatorapp.domain.usecase

import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryUseCase(
    private val historyRepository: HistoryRepository
) {
    suspend operator fun invoke(): Flow<List<HistoryEntity>> {
        return historyRepository.getAllHistory()
    }
}