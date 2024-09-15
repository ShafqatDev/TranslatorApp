package com.example.translatorapp.data.controller

import android.util.Log
import com.example.translatorapp.data.response.NetworkResponse
import com.example.translatorapp.data.response.RequestType
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

object NetworkClient {
    private val jsonHelper = Json {
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
        prettyPrint = true
        coerceInputValues = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        useArrayPolymorphism = false
        allowSpecialFloatingPointValues = true
    }
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(jsonHelper)
        }
    }

    suspend inline fun makeNetworkRequest(
        url: String,
        requestType: RequestType,
    ): String {
        Log.d("makeNetworkRequest", "makeNetworkRequest: $url")
        return withContext(Dispatchers.IO) {
            val response: String = requestType.makeHttpBuilder(url) {}.body()
            response
        }
    }

    suspend fun RequestType.makeHttpBuilder(
        url: String, callback: (HttpRequestBuilder) -> Unit
    ): HttpResponse {
        return when (this) {
            is RequestType.Get -> {
                client.get(url) {
                    callback.invoke(this)
                }
            }

            is RequestType.Post -> {
                client.post(url) {
                    callback.invoke(this)
                    setBody(body)
                }
            }
        }
    }
}