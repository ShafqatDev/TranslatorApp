package com.example.translatorapp.data.data_source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.translatorapp.data.data_source.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Upsert
    suspend fun insertNote(historyEntity: HistoryEntity)

    @Query("UPDATE historyentity SET isFavorite = NOT isFavorite WHERE id = :id")
    suspend fun toggleFavorite(id: Long)

    @Query("SELECT * FROM historyentity")
    fun getNotes(): Flow<List<HistoryEntity>>
}