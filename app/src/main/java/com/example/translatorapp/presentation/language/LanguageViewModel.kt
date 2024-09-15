package com.example.translatorapp.presentation.language

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.translatorapp.data.data_source.model.LanguageModel
import com.example.translatorapp.data.data_source.model.languageList
import com.example.translatorapp.data.controller.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LanguageState(
    val searchField: String = "",
    val originalList: List<LanguageModel> = languageList,
    val isSearchActive: Boolean = false
)

class LanguageViewModel(
    private val sharedPreferences: SharedPreferences,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = MutableStateFlow(LanguageState())
    val state = _state.asStateFlow()

    fun onSearchFieldChange(search: String) {
        _state.update {
            it.copy(
                searchField = search
            )
        }
    }

    private val isFrom = savedStateHandle.get<String>("isFrom")?.toBooleanStrictOrNull() ?: true
    fun saveLanguage(language: LanguageModel) {
        if (isFrom) {
            sharedPreferences.saveFromLanguage(language.shortCode)
        } else {
            sharedPreferences.saveToLanguage(language.shortCode)
        }
    }

    fun onSearchToggle(isSearchActive: Boolean) {
        _state.update {
            it.copy(
                isSearchActive = isSearchActive
            )
        }
    }
}