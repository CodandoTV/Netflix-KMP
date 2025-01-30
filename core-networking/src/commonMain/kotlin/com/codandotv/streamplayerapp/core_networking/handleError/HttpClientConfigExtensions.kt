package com.codandotv.streamplayerapp.core_networking.handleError

import android.util.Log
import com.squareup.moshi.Moshi
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.SerializationException

internal fun HttpClientConfig<OkHttpConfig>.validator(
    moshi: Moshi
) {
    HttpResponseValidator {
        validateResponse { response ->
            when (response.status.value) {
                in 200..299 -> Unit
                in 400..499 -> {
                    val errorBody = response.bodyAsText()
                    val error = try {
                        moshi.adapter(NetworkResponse.Error::class.java).fromJson(errorBody)
                    } catch (e: Exception) {
                        throw Failure.UnparsableResponseException(throwable = e)
                    }
                    throw Failure.ClientException(throwable = error?.exception)
                }

                in 500..599 -> throw Failure.ServerError(response.status.value)
                else -> throw Failure.UnknownError(response.status.value)
            }
        }

        handleResponseExceptionWithRequest { exception, _ ->
            Log.d("HttpClientError",exception.stackTraceToString())

            when (exception) {
                is IOException -> throw Failure.NetworkError(throwable = exception)
                else -> throw Failure.GenericError(
                    msg = exception.message ?: "Unknown error"
                )
            }
        }
    }
}

suspend inline fun <reified T> HttpClient.safeRequest(
    block: HttpRequestBuilder.() -> Unit,
): NetworkResponse<T> =
    try {
        val response = request { block() }
        NetworkResponse.Success(response.body())
    } catch (exception: ClientRequestException) {
        NetworkResponse.Error(
            body = exception.response.bodyAsText(),
            exception = Failure.ServerError(exception.response.status.value)
        )
    } catch (e: SerializationException) {
        NetworkResponse.Error(
            exception = Failure.UnparsableResponseException(throwable = e)
        )
    } catch (e: IOException) {
        NetworkResponse.Error(
            exception = Failure.NetworkError(throwable = e)
        )
    } catch (e: Exception) {
        NetworkResponse.Error(
            exception = Failure.GenericError(msg = e.message ?: "Unknown error")
        )
    }
