package com.geektech.less1quizappkt2.ui.main.quiz_page

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.fragment.findNavController
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.databinding.FragmentQuizPageBinding
import com.geektech.less1quizappkt2.extensions.toast
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class QuizPageFragment : BaseFragment<FragmentQuizPageBinding>(FragmentQuizPageBinding::inflate) {

    private var currentPr = 10

    private var quizType = ""

    override fun checkConnectionNetworkState() {
    }

    override fun setUpUI() {
        vb.imageBack.setOnClickListener { findNavController().navigateUp() }

        val questTypes = arrayOf("boolean", "multiple")
        val randomQuest = Random.nextInt(questTypes.size)
        quizType = questTypes[randomQuest]
        toast(quizType)

        if (quizType == "boolean") {
            vb.group2OptionAnswers.visibility = VISIBLE
            vb.groupMultiChoiceButtons.visibility = GONE
        } else if (quizType == "multiple") {
            vb.group2OptionAnswers.visibility = GONE
            vb.groupMultiChoiceButtons.visibility = VISIBLE
        }

        vb.linearProgressB.progress = currentPr
        vb.buttonSkip.setOnClickListener {
            currentPr += 10
            vb.linearProgressB.progress = currentPr
            vb.linearProgressB.max = 100
            vb.tvProgressBValue.text = (currentPr / 10).toString()
        }
    }

    override fun liveData() {
    }
}