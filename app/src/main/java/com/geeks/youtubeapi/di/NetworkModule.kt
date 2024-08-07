package com.geeks.youtubeapi.di

import com.geeks.youtubeapi.data.api_services.YouTubeApiService
import okhttp3.OkHttpClient
import com.geeks.youtubeapi.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single { provideApiService(get()) }
    single { provideRetrofit(get()) }
    single { provideOkHttpClient(get()) }
    single { provideInterceptor() }
}

fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(okHttpClient)
    .build()

fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor
): OkHttpClient = OkHttpClient.Builder()
    .writeTimeout(15, TimeUnit.SECONDS)
    .readTimeout(15, TimeUnit.SECONDS)
    .connectTimeout(15, TimeUnit.SECONDS)
    .addInterceptor(interceptor)
    .build()

fun provideInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun provideApiService(retrofit: Retrofit): YouTubeApiService =
    retrofit.create(YouTubeApiService::class.java)