package com.geektech.less1quizappkt2.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.data.model.remote.AllQuestions
import com.geektech.less1quizappkt2.data.network.result.Resource
import com.geektech.less1quizappkt2.data.network.result.Status
import com.geektech.less1quizappkt2.databinding.FragmentMainBinding
import com.geektech.less1quizappkt2.extensions.toast
import com.geektech.less1quizappkt2.utils.CheckConnectionState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(), SeekBar.OnSeekBarChangeListener {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)

    private val viewModel: ViewModel by viewModel()
    private val ccs: CheckConnectionState by lazy { CheckConnectionState(requireActivity().application) }
    private var isConnected = true
//    private lateinit var seekBarAmountTxt: String

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        vb.tvSeekBarTick.text = progress.toString()
    }

    override fun setUI() {
        vb.seekBarMain.setOnSeekBarChangeListener(this)

        vb.buttonStartMain.setOnClickListener {
            loadData(vb.tvSeekBarTick.text.toString(), 18, vb.autoCompleteTVDifficulty.text.toString())
        }
    }

    override fun liveData() {
        loadData(vb.tvSeekBarTick.text.toString(), null, null)
    }

    private fun loadData(amountTxt: String, category: Int?, difficulty: String?) {
        viewModel.loadQuestion(amountTxt.toInt(), category, difficulty).observe(this, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {

                    setDDCategoriesButtons(resource)
                    setDDDifficultiesButtons(resource)

                    requireContext().toast(resource.data?.response_code.toString())
                    requireContext().toast(resource.data?.results?.get(0)?.question.toString())
                    requireContext().toast(resource.data?.results?.get(0)?.incorrect_answers.toString())
                }
                Status.ERROR -> requireContext().toast(resource.message + resource.code)
                Status.LOADING -> requireContext().toast("Loading")
            }
        })
    }

    override fun checkConnectionNetworkState() = ccs.observe(this, { isConnected = it })

    private fun setDDCategoriesButtons(resource: Resource<AllQuestions?>) {
        var categories = mutableListOf<String>()
        if (resource.data?.results != null) {
            for (items in resource.data.results) {
                if (items.category != null) {
                    categories = mutableListOf(items.category)
                }
            }
        }
        val categoriesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, categories)
        vb.autoCompleteTVCategory.setAdapter(categoriesAdapter)
        vb.autoCompleteTVCategory.setDropDownBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.backgr_all
            )
        )
    }

    private fun setDDDifficultiesButtons(resource: Resource<AllQuestions?>) {
        var difficulties = mutableListOf<String>()
        if (resource.data?.results != null) {
            for (items in resource.data.results) {
                if (items.difficulty != null) {
                    difficulties = mutableListOf(items.difficulty)
                }
            }
        }
        val difficultiesAdapter =
            ArrayAdapter(requireContext(), R.layout.dropdown_items, difficulties)
        vb.autoCompleteTVDifficulty.setAdapter(difficultiesAdapter)
        vb.autoCompleteTVDifficulty.setDropDownBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.backgr_all
            )
        )
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {

    }
}