package com.example.translatorapp.presentation.camera

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translatorapp.data.controller.SharedPreferences
import com.example.translatorapp.domain.usecase.TranslateTextUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CameraState(
    val detectedText: String = "No text detected yet..",
    val fromLanguage: String = "en",
    val toLanguage: String = "ur"
)

class CameraViewModel(
    private val sharedPreferences: SharedPreferences,
    private val translatorTextUseCase: TranslateTextUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    init {
        updateLanguages()
    }

    fun updateLanguages() {
        _state.update {
            it.copy(
                fromLanguage = sharedPreferences.getFromLanguage(),
                toLanguage = sharedPreferences.getToLanguage()
            )
        }
    }

    fun onTextUpdated(updatedText: String) {
        _state.update { currentState ->
            currentState.copy(detectedText = updatedText)
        }
        getTranslatorText()
    }

    private fun getTranslatorText() {
        val fromLanguage = sharedPreferences.getFromLanguage()
        val toLanguage = sharedPreferences.getToLanguage()
        viewModelScope.launch(Dispatchers.IO) {
            val response = translatorTextUseCase.invoke(
                _state.value.detectedText, from = fromLanguage, to = toLanguage
            )
            _state.update {
                it.copy(detectedText = response)
            }
        }
    }

    fun onSwapClick() {
        val fromLanguage = sharedPreferences.getFromLanguage()
        val toLanguage = sharedPreferences.getToLanguage()
        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferences.saveFromLanguage(toLanguage)
            sharedPreferences.saveToLanguage(fromLanguage)
            _state.update {
                it.copy(
                    toLanguage = fromLanguage, fromLanguage = toLanguage
                )
            }
        }
    }
}
