package com.example.translatorapp.presentation.translator_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translatorapp.utils.SharedPreferences
import com.example.translatorapp.utils.TranslatorHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TranslatorState(
    val requestText: String = "",
    val responseText: String = "",
    val fromLanguage: String = "en",
    val toLanguage: String = "ur",
    val isRateUsDialogOpen: Boolean = false
)


class TranslatorViewModel (
    private val translatorHelper: TranslatorHelper,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _state = MutableStateFlow(TranslatorState())
    val state = _state.asStateFlow()


    init {
        updateLanguages()
    }

    fun updateLanguages() {
        _state.update {
            it.copy(
                fromLanguage = sharedPreferences.getFromLanguage() ?: "",
                toLanguage = sharedPreferences.getToLanguage() ?: ""
            )
        }
    }

    fun onTranslatorTextFieldChange(text: String) {
        _state.update {
            it.copy(
                requestText = text
            )
        }
    }

    fun onSpeechToText(text: String) {
        _state.update {
            it.copy(
                requestText = it.requestText + " " + text
            )
        }
    }

    fun getTranslatorText() {
        val fromLanguage = sharedPreferences.getFromLanguage() ?: "en"
        Log.d("cvv", "fromLanguage:$fromLanguage ")
        val toLanguage = sharedPreferences.getToLanguage() ?: "ur"
        Log.d("cvv", "toLanguage:$toLanguage ")


        viewModelScope.launch(Dispatchers.IO) {
            val response = translatorHelper.getTranslator(
                _state.value.requestText,
                from = fromLanguage ?: "",
                to = toLanguage ?: ""
            )

            _state.update {
                it.copy(
                    responseText = response ?: ""
                )
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
                    responseText = state.value.requestText,
                    requestText = state.value.responseText,
                    toLanguage = fromLanguage,
                    fromLanguage = toLanguage
                )
            }
        }
    }
    fun hideShowRateUsDialog() {
        _state.update {
            it.copy(
                isRateUsDialogOpen = state.value.isRateUsDialogOpen.not()
            )
        }
    }
}