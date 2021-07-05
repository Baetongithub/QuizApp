package com.geektech.less1quizappkt2.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.less1quizappkt2.databinding.FragmentSecondBinding
import com.geektech.less1quizappkt2.extensions.toast
import com.geektech.less1quizappkt2.model.History

class SecondFragment : Fragment() {

    private lateinit var ui: FragmentSecondBinding

    private val list = mutableListOf<History>()
    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter(
            list,
            requireContext(),
            this::onItemClick
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ui = FragmentSecondBinding.inflate(inflater, container, false)

        list.add(History("Mixed", "10/10", "High"))
        list.add(History("Mixed", "10/10", "Medium"))
        list.add(History("History", "10/10", "High"))
        list.add(History("Astronomy", "10/10", "Medium"))
        list.add(History("Mixed", "10/10", "Medium"))

        ui.recyclerViewHistory.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
            historyAdapter.notifyDataSetChanged()
        }

        return ui.root
    }

    private fun onItemClick(history: History) {
        requireActivity().toast(history.category)
    }
}