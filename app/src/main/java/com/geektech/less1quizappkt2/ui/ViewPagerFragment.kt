package com.geektech.less1quizappkt2.ui

import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.databinding.FragmentViewPagerBinding
import com.geektech.less1quizappkt2.ui.history.HistoryFragment
import com.geektech.less1quizappkt2.ui.main.MainFragment
import com.geektech.less1quizappkt2.ui.profile.ProfileFragment
import com.google.android.material.navigation.NavigationBarView

class ViewPagerFragment : BaseFragment<FragmentViewPagerBinding>(FragmentViewPagerBinding::inflate) {

    override fun checkConnectionNetworkState() {
    }

    override fun setUpUI() {
        val vpAdapter = VPAdapter(requireActivity().supportFragmentManager, lifecycle)
        val fragList = listOf(MainFragment(), HistoryFragment(), ProfileFragment())
        vpAdapter.fragList.addAll(fragList)
        vb.viewPager2.adapter = vpAdapter
        vb.viewPager2.isUserInputEnabled = false

        vb.navView.setOnItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener =
        NavigationBarView.OnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_explore -> {
                    vb.viewPager2.currentItem = 0
                    return@OnItemSelectedListener true
                }
                R.id.navigation_map -> {
                    vb.viewPager2.currentItem = 1
                    return@OnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    vb.viewPager2.currentItem = 2
                    return@OnItemSelectedListener true
                }
            }
            false
        }

    override fun liveData() {
    }
}