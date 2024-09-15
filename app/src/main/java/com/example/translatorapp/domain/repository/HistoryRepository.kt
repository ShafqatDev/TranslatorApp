package com.example.translatorapp.domain.repository

import com.example.translatorapp.data.data_source.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun insertHistory(translator: HistoryEntity)
    suspend fun getHistoryById(id: Long): HistoryEntity?
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)
    suspend fun deleteHistory(id: Long)
    suspend fun getAllHistory(): Flow<List<HistoryEntity>>
}