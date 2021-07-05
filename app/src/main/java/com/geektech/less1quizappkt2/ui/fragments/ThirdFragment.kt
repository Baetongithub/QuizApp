package com.geektech.less1quizappkt2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geektech.less1quizappkt2.BuildConfig
import com.geektech.less1quizappkt2.databinding.FragmentThirdBinding
import com.geektech.less1quizappkt2.extensions.toFormat
import com.geektech.less1quizappkt2.extensions.toast

class ThirdFragment : Fragment() {

    private lateinit var ui: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ui = FragmentThirdBinding.inflate(inflater, container, false)
        ui.tvVersionSettings.text = ("v " + BuildConfig.VERSION_NAME).toFormat()

        initClicks()

       return ui.root
    }

    private fun initClicks() {
        ui.tvSettings.setOnClickListener { requireContext().toast(ui.tvSettings.text.toString()) }
        ui.tvRateUs.setOnClickListener { requireContext().toast(ui.tvRateUs.text.toString()) }
        ui.tvLeaveFeedback.setOnClickListener { requireContext().toast(ui.tvLeaveFeedback.text.toString()) }
        ui.tvClearHistory.setOnClickListener { requireContext().toast(ui.tvClearHistory.text.toString()) }
    }
}