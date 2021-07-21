package com.geektech.less1quizappkt2.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var binding: VB? = null
    val vb get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = viewBinding(inflater, container)

        setUI()
        liveData()
        checkConnectionNetworkState()

        return vb.root
    }

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun setUI()
    abstract fun liveData()
    abstract fun checkConnectionNetworkState()
}