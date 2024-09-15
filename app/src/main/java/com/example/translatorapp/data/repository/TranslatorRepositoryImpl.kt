package com.example.translatorapp.data.repository

import com.example.translatorapp.core.constants.LocalData
import com.example.translatorapp.core.constants.toEncode
import com.example.translatorapp.data.controller.NetworkClient
import com.example.translatorapp.data.response.RequestType
import com.example.translatorapp.domain.repository.TranslatorRepository

class TranslatorRepositoryImpl : TranslatorRepository {
    override suspend fun getTranslator(
        text: String, from: String, to: String
    ): String {
        val api =
            "https://translate.googleapis.com/translate_a/single?client=gtx&sl=$from&tl=$to&dt=t&q=${text.toEncode()}"
        val getResponse = NetworkClient.makeNetworkRequest(
            url = api, requestType = RequestType.Get
        )
        val response = LocalData.extractFromString(getResponse)
        return response
    }
}