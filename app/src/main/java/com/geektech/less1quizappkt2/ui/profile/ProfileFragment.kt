package com.geektech.less1quizappkt2.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.less1quizappkt2.BuildConfig
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.databinding.FragmentProfileBinding
import com.geektech.less1quizappkt2.extensions.toast

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {



    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding =
        FragmentProfileBinding.inflate(inflater, container, false)

    override fun setUpUI() {
        vb.tvVersionSettings.text = ("v ${BuildConfig.VERSION_NAME}")

        initClicks()
    }

    override fun liveData() {

    }

    override fun checkConnectionNetworkState() {

    }

    private fun initClicks() {
        vb.tvSettings.setOnClickListener { requireContext().toast("1. This feature is being developed") }
        vb.tvRateUs.setOnClickListener { requireContext().toast("2. This feature is being developed") }
        vb.tvLeaveFeedback.setOnClickListener { requireContext().toast("3. This feature is being developed") }
        vb.tvClearHistory.setOnClickListener { requireContext().toast("4. This feature is being developed") }
    }
}