package com.geektech.less1quizappkt2.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.databinding.FragmentMainBinding
import com.geektech.less1quizappkt2.extensions.toast

class MainFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var ui: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ui = FragmentMainBinding.inflate(inflater, container, false)
        ui.seekBarMain.setOnSeekBarChangeListener(this)

        setUpDropDownButtons()

        ui.buttonStartMain.setOnClickListener { requireActivity().toast(ui.buttonStartMain.text.toString()) }

        return ui.root
    }

    private fun setUpDropDownButtons() {
        val categories = resources.getStringArray(R.array.category_array)
        val categoriesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, categories)
        ui.autoCompleteTVCategory.setAdapter(categoriesAdapter)
        ui.autoCompleteTVCategory.setDropDownBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.backgr_all
            )
        )

        val difficulties = resources.getStringArray(R.array.difficulty_array)
        val difficultiesAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_items, difficulties)
        ui.autoCompleteTVDifficulty.setAdapter(difficultiesAdapter)
        ui.autoCompleteTVDifficulty.setDropDownBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.backgr_all
            )
        )
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        ui.tvSeekBarTick.text = progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}