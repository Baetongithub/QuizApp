package com.geektech.less1quizappkt2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.databinding.ActivityMainBinding
import com.geektech.less1quizappkt2.ui.fragments.MainFragment
import com.geektech.less1quizappkt2.ui.fragments.ThirdFragment
import com.geektech.less1quizappkt2.ui.history.SecondFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0,0)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        val vpAdapter = VPAdapter(this)
        val fragList = listOf(MainFragment(), SecondFragment(), ThirdFragment())
        vpAdapter.fragList.addAll(fragList)
        ui.viewPager2.adapter = vpAdapter
        ui.viewPager2.isUserInputEnabled = false

        ui.navView.setOnItemSelectedListener (onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener =
        NavigationBarView.OnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_explore -> {
                    ui.viewPager2.currentItem = 0
                    return@OnItemSelectedListener true
                }
                R.id.navigation_map -> {
                    ui.viewPager2.currentItem = 1
                    return@OnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    ui.viewPager2.currentItem = 2
                    return@OnItemSelectedListener true
                }
            }
            false
        }
}