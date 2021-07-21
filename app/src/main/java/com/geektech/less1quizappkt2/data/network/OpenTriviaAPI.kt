package com.geektech.less1quizappkt2.data.network

import com.geektech.less1quizappkt2.data.model.remote.AllQuestions
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenTriviaAPI {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount")
        amount: String,
        @Query("category")
        category: String,
        @Query("difficulty")
        difficulty: String
    ): Response<AllQuestions>
}