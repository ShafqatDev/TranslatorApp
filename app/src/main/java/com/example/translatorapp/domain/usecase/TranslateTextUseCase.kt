package com.example.translatorapp.domain.usecase

import com.example.translatorapp.domain.repository.TranslatorRepository

class TranslateTextUseCase(
    private val translatorRepository: TranslatorRepository
) {
    suspend operator fun invoke(text: String, from: String, to: String) =
        translatorRepository.getTranslator(text, from, to)
}