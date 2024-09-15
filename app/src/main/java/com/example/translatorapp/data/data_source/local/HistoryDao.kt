package com.example.translatorapp.data.data_source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.translatorapp.data.data_source.model.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {
    @Upsert
    suspend fun insertHistory(translator: HistoryEntity)

    @Query("SELECT * FROM HistoryEntity WHERE id = :id")
    suspend fun getHistoryById(id: Long): HistoryEntity?

    @Query("UPDATE HistoryEntity SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("DELETE FROM HistoryEntity WHERE id = :id")
    suspend fun deleteHistory(id: Long)

    @Query("SELECT * FROM HistoryEntity")
    fun getAllHistory(): Flow<List<HistoryEntity>>
}
