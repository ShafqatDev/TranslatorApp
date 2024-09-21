package com.example.translatorapp.data.repository

import com.example.translatorapp.data.data_source.local.HistoryDao
import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow

class HistoryRepositoryImpl(private val dao: HistoryDao) : HistoryRepository {

    override fun getNotes(): Flow<List<HistoryEntity>> = dao.getNotes()

    override suspend fun toggleFavorite(id: Long) {
        dao.toggleFavorite(id)
    }

    override suspend fun insertNote(note: HistoryEntity) {
        dao.insertNote(note)
    }
}
