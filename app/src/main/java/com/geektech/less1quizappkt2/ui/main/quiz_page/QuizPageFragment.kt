package com.geektech.less1quizappkt2.ui.main.quiz_page

import androidx.navigation.fragment.findNavController
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.databinding.FragmentQuizPageBinding

class QuizPageFragment : BaseFragment<FragmentQuizPageBinding>(FragmentQuizPageBinding::inflate) {

    override fun checkConnectionNetworkState() {
    }

    override fun setUpUI() {
        vb.imageBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun liveData() {
    }
}