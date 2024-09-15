package com.example.translatorapp.data.data_source.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = System.currentTimeMillis(),
    val message: String,
    val translation: String,
    val isFavorite: Boolean = false,
)
