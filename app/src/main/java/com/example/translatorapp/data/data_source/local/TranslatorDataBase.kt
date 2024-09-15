package com.example.translatorapp.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.translatorapp.data.data_source.model.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class TranslatorDataBase:RoomDatabase() {
    abstract val historyDao: HistoryDao
}