package com.geektech.less1quizappkt2.ui.profile

import com.geektech.less1quizappkt2.BuildConfig
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.databinding.FragmentProfileBinding
import com.geektech.less1quizappkt2.extensions.toast

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    override fun setUpUI() {
        vb.tvVersionSettings.text = (String.format("v ${BuildConfig.VERSION_NAME}"))

        initClicks()
    }

    override fun liveData() {

    }

    override fun checkConnectionNetworkState() {

    }

    private fun initClicks() {
        vb.tvSettings.setOnClickListener { toast("1. This feature is being developed") }
        vb.tvRateUs.setOnClickListener { toast("2. This feature is being developed") }
        vb.tvLeaveFeedback.setOnClickListener { toast("3. This feature is being developed") }
        vb.tvClearHistory.setOnClickListener { toast("4. This feature is being developed") }
    }
}