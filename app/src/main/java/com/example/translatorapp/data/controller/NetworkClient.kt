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
    val jsonHelper = Json {
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

    suspend fun makeNetworkRequest(
        url: String,
    ): String {
        Log.d("makeNetworkRequest", "makeNetworkRequest: $url")
        return withContext(Dispatchers.IO) {
            val response: String = client.get(url).body()
            response
        }
    }

    suspend inline fun <reified T> makeNetworkRequest(
        url: String,
        requestType: RequestType,
        headers: Map<String, String>? = null,
    ): NetworkResponse<T> {
        Log.d("makeNetworkRequest", "makeNetworkRequest: $url")
        return withContext(Dispatchers.IO) {
            try {
                val response: String = requestType.getHttpBuilder(url) {
                    Log.d("makeNetworkRequest", "request builder${url}")

                    if (requestType is RequestType.Post) {
                        it.setBody(requestType.body)
                    }
                    headers?.let { headers ->
                        headers.forEach { (key, value) ->
                            it.header(key, value)
                        }
                    }
                    Log.d("cvv", "${it.body}")
                }.body()
                Log.d("makeNetworkRequest", "Network Response:$response")
                val newResponse: T = jsonHelper.decodeFromString(response)
                (NetworkResponse.Success(newResponse))

            } catch (e: ClientRequestException) {
                Log.d("makeNetworkRequest", "Network ClientRequestException:${e.message}")

                (NetworkResponse.Error(e.message))
            } catch (e: ServerResponseException) {
                Log.d("makeNetworkRequest", "Network ServerResponseException:${e.message}")

                (NetworkResponse.Error(e.message))
            } catch (e: Exception) {
                Log.d("makeNetworkRequest", "Network Exception:${e.message}")
                (NetworkResponse.Error(e.message ?: "Unknown error"))
            }
        }
    }

    suspend fun RequestType.getHttpBuilder(
        url: String,
        callback: (HttpRequestBuilder) -> Unit
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