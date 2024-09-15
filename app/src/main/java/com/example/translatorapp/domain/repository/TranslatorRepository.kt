package com.example.translatorapp.domain.repository

interface TranslatorRepository {
    suspend fun getTranslator(text: String, from: String, to: String): String
}