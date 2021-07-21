package com.geektech.less1quizappkt2.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.less1quizappkt2.BuildConfig
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.databinding.FragmentProfileBinding
import com.geektech.less1quizappkt2.extensions.toast

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding =
        FragmentProfileBinding.inflate(inflater, container, false)

    override fun setUI() {
        vb.tvVersionSettings.text = ("v ${BuildConfig.VERSION_NAME}")

        initClicks()
    }

    override fun liveData() {
    }

    override fun checkConnectionNetworkState() {

    }

    private fun initClicks() {
        vb.tvSettings.setOnClickListener { requireContext().toast(vb.tvSettings.text.toString()) }
        vb.tvRateUs.setOnClickListener { requireContext().toast(vb.tvRateUs.text.toString()) }
        vb.tvLeaveFeedback.setOnClickListener { requireContext().toast(vb.tvLeaveFeedback.text.toString()) }
        vb.tvClearHistory.setOnClickListener { requireContext().toast(vb.tvClearHistory.text.toString()) }
    }
}