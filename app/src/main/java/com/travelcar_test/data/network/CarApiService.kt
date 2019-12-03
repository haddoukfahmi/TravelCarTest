package com.travelcar_test.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.travelcar_test.R
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object CarApiService {

    val application = com.travelcar_test.Application
        private fun cacheHttp(): Cache {
            val cacheSize: Long = 10 * 1024 * 1024

            return Cache(application.instance.cacheDir, cacheSize)
        }
        fun makeApiService(): CarsApi {
            return Retrofit.Builder()
                .baseUrl(application.instance.getString(R.string.base_url))
                .client(makeOkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(CarsApi::class.java)
        }

        private fun makeOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(makeLoggingInterceptor())
                .cache(cacheHttp())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .build()
        }

        private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level =
                HttpLoggingInterceptor.Level.BODY
            return logging
        }
}