package com.example.translatorapp.di

import androidx.room.Room
import com.example.translatorapp.presentation.talking_translator.LanguageChatViewModel
import com.example.translatorapp.data.repository.CameraRepositoryImpl
import com.example.translatorapp.data.repository.TranslatorRepositoryImpl
import com.example.translatorapp.domain.repository.CameraRepository
import com.example.translatorapp.domain.repository.TranslatorRepository
import com.example.translatorapp.presentation.camera.CameraViewModel
import com.example.translatorapp.presentation.history.HistoryViewModel
import com.example.translatorapp.presentation.language.LanguageViewModel
import com.example.translatorapp.presentation.translate.TranslatorViewModel
import com.example.translatorapp.data.controller.SharedPreferences
import com.example.translatorapp.data.data_source.local.HistoryDao
import com.example.translatorapp.data.data_source.local.TranslatorDataBase
import com.example.translatorapp.data.repository.HistoryRepositoryImpl
import com.example.translatorapp.domain.repository.HistoryRepository
import com.example.translatorapp.domain.usecase.DeleteHistoryUseCase
import com.example.translatorapp.domain.usecase.GetHistoryByIdUseCase
import com.example.translatorapp.domain.usecase.GetHistoryUseCase
import com.example.translatorapp.domain.usecase.InsertHistoryUseCase
import com.example.translatorapp.domain.usecase.TranslateTextUseCase
import com.example.translatorapp.domain.usecase.UpdateFavHistoryUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesList = module {
    factory<CameraRepository> {
        CameraRepositoryImpl(get())
    }
    factory<TranslatorRepository> {
        TranslatorRepositoryImpl()
    }
    viewModel {
        HistoryViewModel(get())
    }
    viewModel {
        LanguageChatViewModel(get(), get())
    }
    viewModel {
        CameraViewModel(get(), get())
    }
    viewModel {
        TranslatorViewModel(get(), get())
    }
    viewModel {
        LanguageViewModel(get(), get())
    }
    single {
        SharedPreferences(get())
    }
    single {
        TranslateTextUseCase(get())
    }
    single<TranslatorDataBase> {
        Room.databaseBuilder(
            get(), TranslatorDataBase::class.java, "translator_db"
        ).build()
    }
    factory<HistoryDao> {
        get<TranslatorDataBase>().historyDao
    }
    factory<HistoryRepository> {
        HistoryRepositoryImpl(get())
    }
    single {
        InsertHistoryUseCase(get())
    }
    single {
        DeleteHistoryUseCase(get())
    }
    single {
        GetHistoryUseCase(get())
    }
    single {
        GetHistoryByIdUseCase(get())
    }
    single {
        UpdateFavHistoryUseCase(get())
    }
}