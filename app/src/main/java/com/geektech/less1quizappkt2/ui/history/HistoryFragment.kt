package com.geektech.less1quizappkt2.ui.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.databinding.FragmentHistoryBinding
import com.geektech.less1quizappkt2.extensions.toast
import com.geektech.less1quizappkt2.data.model.local.History
import com.geektech.less1quizappkt2.utils.CheckConnectionState

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::inflate) {

    private var isConnected = false
    private val ccs: CheckConnectionState by lazy { CheckConnectionState(requireActivity().application) }
    private val list = mutableListOf<History>()
    private val historyAdapter: HistoryAdapter by lazy { HistoryAdapter(list, requireContext(), this::onItemClick) }

    override fun checkConnectionNetworkState() = ccs.observe(this) { isConnected = it }

    @SuppressLint("NotifyDataSetChanged")
    override fun setUpUI() {
        vb.recyclerViewHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
            historyAdapter.notifyDataSetChanged()
        }
    }

    override fun liveData() {
        list.add(History("Mixed", "10/10", "High"))
        list.add(History("Mixed", "10/10", "Medium"))
        list.add(History("History", "10/10", "High"))
        list.add(History("Astronomy", "10/10", "Medium"))
        list.add(History("Mixed", "10/10", "Medium"))
    }

    private fun onItemClick(history: History) {
        requireActivity().toast(history.category)
    }
}