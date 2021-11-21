package com.erwin2687.amovie.network

import com.erwin2687.amovie.BuildConfig
import com.erwin2687.amovie.BuildConfig.API_KEY
import com.erwin2687.amovie.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRepository {
    companion object {
    private val client: OkHttpClient =
        buildClient()
    private val retrofit: Retrofit =
        buildRetrofit(
            client
        )


    private fun buildClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        var builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .addInterceptor {
                val url = it.request()
                    .url
                    .newBuilder()
                    .addQueryParameter("api_key",
                        API_KEY
                    )
                    .build()

                val request = it.request().newBuilder().url(url).build()

                it.proceed(request)
            }
        if (BuildConfig.DEBUG) {
            builder = builder.addInterceptor(loggingInterceptor)
        }
        return builder.build()
    }

    private fun buildRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

}
}