package com.example.translatorapp.domain.repository

import com.example.translatorapp.data.data_source.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun insertNote(note: HistoryEntity)
    suspend fun toggleFavorite(id: Long)
    fun getNotes(): Flow<List<HistoryEntity>>
}