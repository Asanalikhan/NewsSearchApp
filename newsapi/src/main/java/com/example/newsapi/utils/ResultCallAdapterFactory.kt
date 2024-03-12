package com.example.newsapi.utils

import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultCallAdapterFactory private constructor() : CallAdapter.Factory() {

    companion object {
        @JvmStatic
        fun create(): CallAdapter.Factory {
            return ResultCallAdapterFactory()
        }
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        if (getRawType(returnType) != Result::class.java) {
            return null
        }
        if (returnType !is ParameterizedType) {
            throw IllegalStateException("Result must have generic type (e.g., Result<Data>)")
        }
        val responseType = getParameterUpperBound(0, returnType)
        return ResultCallAdapter<Any>(responseType)
    }

    private class ResultCallAdapter<T>(private val responseType: Type) : CallAdapter<T, Result<T>> {

        override fun responseType(): Type {
            return responseType
        }

        override fun adapt(call: retrofit2.Call<T>): Result<T> {
            val response = call.execute()
            return if (response.isSuccessful) {
                Result.Success(response.body() ?: throw IllegalStateException("Response body is null"))
            } else {
                val errorBody = response.errorBody()?.string()
                val message = if (errorBody.isNullOrEmpty()) response.message() else errorBody
                Result.Error(Exception(message))
            }
        }
    }

}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
