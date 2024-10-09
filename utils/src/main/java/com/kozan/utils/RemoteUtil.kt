package com.kozan.utils

import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


object RemoteUtil {

    sealed class ApiResponse<out T> {
        data class Success<out T>(val data: T) : ApiResponse<T>()
        data class Error(val message: String) : ApiResponse<Nothing>()
        object Loading : ApiResponse<Nothing>()
    }

/**
 *
 * @param apiServiceClass ApiInterface Class
 * @param baseUrl BASE_URL
 *
 * Usage :
 *
 * val apiService = RemoteUtil.getApiService(ApiInterface::class.java,"BASE_URL")
 *
 * val request = apiService.getContents()
 *
 * */
    fun <T> getApiService(
        apiServiceClass: Class<T>,
        baseUrl: String,
        okHttpClient: OkHttpClient? = null
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val apiService = retrofit.create(apiServiceClass)
        return apiService

    }


   /**
    *  provides safe api requests and clean code
    *
    *  Usage :
    *
    * suspend fun getLanguages(): ApiResponse<List<Language>> {
    *         return safeApiRequest { apiService.getLanguages() }
    *     }
    */

    suspend fun <T> safeApiRequest(apiCall: suspend () -> Response<T>): ApiResponse<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body()?.let {
                    ApiResponse.Success(it)
                } ?: ApiResponse.Error("Response Body is null")
            } else {
                ApiResponse.Error("Error code: ${response.code()}, message: ${response.message()}")
            }

        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: "An unknown error occurred")
        }
    }

    interface Api {
        @GET("contents_{langKey}")
        suspend fun getContents(@Path("langKey") langKey: String): Response<List<ApiResponse>>
    }

}