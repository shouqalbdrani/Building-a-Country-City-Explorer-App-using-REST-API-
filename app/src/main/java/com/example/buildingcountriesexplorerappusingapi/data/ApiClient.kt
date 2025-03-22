package com.example.buildingcountriesexplorerappusingapi.data


import com.example.buildingcountriesexplorerappusingapi.data.apiservice.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//object ApiClient {
//
//    private const val BASE_URL = "https://countriesnow.space/api/v0.1/"
//
//    fun provideApiService(): ApiService {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(ApiService::class.java)
//    }
//}

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


object ApiClient {

    private const val BASE_URL = "https://countriesnow.space/api/v0.1/"

    // Interceptor for adding "App-Version" header
    private val headerInterceptor = Interceptor { chain ->
        val originalRequest: Request = chain.request()
        val newRequest: Request = originalRequest.newBuilder()
            .addHeader("App-Version", "1.0.0")
            .build()
        println("Request Headers: ${newRequest.headers}")
        chain.proceed(newRequest)
    }

    // OkHttpClient with multiple interceptors
    private val client = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor) // App version header
        .addInterceptor { chain ->   // Authentication token
            val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer YOUR_AUTH_TOKEN")
                .build()
            chain.proceed(newRequest)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {  // Logging
            level = HttpLoggingInterceptor.Level.BODY
        })
        .connectTimeout(50, TimeUnit.SECONDS)  // Connection timeout
        .readTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(50, TimeUnit.SECONDS)
        .build()

    // Retrofit setup
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
