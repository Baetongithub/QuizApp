package com.geektech.less1quizappkt2.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.geektech.less1quizappkt2.data.model.remote.AllQuestions
import com.geektech.less1quizappkt2.data.network.OpenTriviaAPI
import com.geektech.less1quizappkt2.data.network.result.Resource
import kotlinx.coroutines.Dispatchers

class Repository(private val openTriviaAPI: OpenTriviaAPI) {
    fun getQuestions(
        amount: Int?,
        category: Int?,
        difficulty: String?
    ): LiveData<Resource<AllQuestions>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            if (category == null && difficulty == null) {
                val response = openTriviaAPI.getQuestions(amount!!, null, null)
                emit(
                    if (response.isSuccessful) Resource.success(response.body())
                    else Resource.error(response.message(), response.body(), response.code())
                )
            } else {
                val response = openTriviaAPI.getQuestions(amount!!, category, difficulty)
                emit(
                    if (response.isSuccessful) Resource.success(response.body())
                    else Resource.error(response.message(), response.body(), response.code())
                )
            }
        }
    }
}