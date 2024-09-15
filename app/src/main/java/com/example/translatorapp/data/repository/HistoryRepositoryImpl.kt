package com.example.translatorapp.data.repository

import com.example.translatorapp.data.data_source.local.HistoryDao
import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(
    private val dao: HistoryDao
) : HistoryRepository {
    override suspend fun insertHistory(translator: HistoryEntity) {
        dao.insertHistory(translator)
    }

    override suspend fun getHistoryById(id: Long): HistoryEntity? {
        return dao.getHistoryById(id)
    }

    override suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) {
        dao.updateFavoriteStatus(id, isFavorite)
    }

    override suspend fun deleteHistory(id: Long) {
        dao.deleteHistory(id)
    }

    override suspend fun getAllHistory(): Flow<List<HistoryEntity>> {
        return dao.getAllHistory()
    }
}