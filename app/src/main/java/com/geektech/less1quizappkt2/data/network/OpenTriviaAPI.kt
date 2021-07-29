package com.geektech.less1quizappkt2.data.network

import com.geektech.less1quizappkt2.data.model.remote.AllQuestions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaAPI {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount")
        amount: Int,
        @Query("category")
        category: Int?,
        @Query("difficulty")
        difficulty: String?
    ): Response<AllQuestions>

    @GET("api.php")
    suspend fun getAllQuestions(
        @Query("amount")
        amount: Int
    ): Response<AllQuestions>
}