package com.example.translatorapp.utils

import android.util.Log
import com.example.translatorapp.core.constants.toEncode
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.json.JSONArray


class TranslatorHelper  {
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getTranslator(text: String, from: String, to: String): String {
        val api =
            "https://translate.googleapis.com/translate_a/single?client=gtx&sl=$from&tl=$to&dt=t&q=${text.toEncode()}"
        val response = client.get(api)
            .body<String>()
        val extracted = extractFromString(response)
        Log.d("cvv", "1.: $response")
        Log.d("cvv", "2.: $extracted")
        return extracted
    }

    private fun extractFromString(rawText: String): String {
        val jsonArray = JSONArray(rawText)
        var textContents = ""
        val thirdList = jsonArray.getJSONArray(0)
        for (innerListIndex in 0 until thirdList.length()) {
            val innerList = thirdList.getJSONArray(innerListIndex)
            val text = innerList.getString(0)
            if (textContents.isNotBlank()) {
                textContents += ""
            }
            textContents += text
        }
        return textContents
    }

    companion object {
        const val TRANSLATOR_API =
            "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=ur&dt=t&q="
    }
}
