package com.geektech.less1quizappkt2.ui

import androidx.lifecycle.LiveData
import com.geektech.less1quizappkt2.base.BaseViewModel
import com.geektech.less1quizappkt2.data.model.remote.AllQuestions
import com.geektech.less1quizappkt2.data.network.result.Resource

class ViewModel(private val repository: Repository) : BaseViewModel() {

    fun loadQuestion(
        amount: String?,
        category: String?,
        difficulty: String?
    ): LiveData<Resource<AllQuestions>> {
        return repository.getQuestions(amount, category, difficulty)
    }
}