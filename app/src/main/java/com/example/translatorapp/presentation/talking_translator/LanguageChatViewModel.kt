package com.example.translatorapp.presentation.talking_translator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translatorapp.domain.usecase.TranslateTextUseCase
import com.example.translatorapp.data.controller.SharedPreferences
import com.example.translatorapp.data.data_source.model.HistoryEntity
import com.example.translatorapp.domain.usecase.InsertHistoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MessageBubbleData(val message: String, val translation: String, val isLeft: Boolean)

data class LanguageChatState(
    val messages: List<MessageBubbleData> = emptyList(),
    val fromLanguage: String = "en",
    val toLanguage: String = "ur",
    val requestText: String = "",
    val responseText: String = ""
)

class LanguageChatViewModel(
    private val translatorTextUseCase: TranslateTextUseCase,
    private val sharedPreferences: SharedPreferences,
//    private val insertHistoryUseCase: InsertHistoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(LanguageChatState())
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

    fun onSwapClick() {
        val fromLanguage = sharedPreferences.getFromLanguage()
        val toLanguage = sharedPreferences.getToLanguage()
        viewModelScope.launch(Dispatchers.IO) {
            sharedPreferences.saveFromLanguage(toLanguage)
            sharedPreferences.saveToLanguage(fromLanguage)
            _state.update {
                it.copy(
                    fromLanguage = toLanguage, toLanguage = fromLanguage
                )
            }
        }
    }

    fun onMicButtonClick(isLeft: Boolean, spokenText: String) {
        viewModelScope.launch {
            val fromLanguage = if (isLeft) _state.value.fromLanguage else _state.value.toLanguage
            val toLanguage = if (isLeft) _state.value.toLanguage else _state.value.fromLanguage
            addMessage(spokenText, "", isLeft)
            val translatedText = translatorTextUseCase.invoke(spokenText, fromLanguage, toLanguage)
            addMessage(translatedText, spokenText, !isLeft)
        }
    }

    private fun addMessage(message: String, translation: String, isLeft: Boolean) {
        viewModelScope.launch {
            val newMessage =
                MessageBubbleData(message = message, translation = translation, isLeft = isLeft)
            _state.update { currentState ->
                currentState.copy(
                    messages = currentState.messages + newMessage,
                )
            }
            val historyItem = HistoryEntity(
                message = message,
                translation = translation,
            )
//            insertHistoryUseCase.invoke(historyItem)
        }
    }
}
