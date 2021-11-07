package com.geektech.less1quizappkt2.di

import com.geektech.less1quizappkt2.BuildConfig.BASE_URL
import com.geektech.less1quizappkt2.data.network.OpenTriviaAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModules = module {
    single { provideNetwork(get()) }
    factory { provideOkHttpClient() }
    single { provideQuestions(get()) }
}

fun provideNetwork(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    return OkHttpClient().newBuilder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

fun provideQuestions(retrofit: Retrofit): OpenTriviaAPI = retrofit.create(OpenTriviaAPI::class.java)