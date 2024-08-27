package com.example.translatorapp.di

import com.example.translatorapp.presentation.language_screen.LanguageViewModel
import com.example.translatorapp.presentation.translator_screen.TranslatorViewModel
import com.example.translatorapp.utils.TranslatorHelper
import com.example.translatorapp.utils.SharedPreferences
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modulesList = module {
    viewModel {
        TranslatorViewModel(get(), get())
    }
    viewModel {
        LanguageViewModel(get(),get())
    }
    single {
        TranslatorHelper()
    }
    single {
        SharedPreferences(get())
    }
}