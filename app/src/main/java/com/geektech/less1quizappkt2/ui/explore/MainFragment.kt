package com.geektech.less1quizappkt2.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.base.BaseFragment
import com.geektech.less1quizappkt2.data.model.remote.categories.Categories
import com.geektech.less1quizappkt2.data.network.result.Resource
import com.geektech.less1quizappkt2.data.network.result.Status
import com.geektech.less1quizappkt2.databinding.FragmentMainBinding
import com.geektech.less1quizappkt2.extensions.toast
import com.geektech.less1quizappkt2.utils.CheckConnectionState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(), SeekBar.OnSeekBarChangeListener {

    private val viewModel: ViewModel by viewModel()
    private val ccs: CheckConnectionState by lazy { CheckConnectionState(requireActivity().application) }
    private var isConnected = false
//    private lateinit var seekBarAmountTxt: String

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun checkConnectionNetworkState() = ccs.observe(this, { isConnected = it })

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        vb.tvSeekBarTick.text = progress.toString()
    }

    override fun setUpUI() {
        vb.seekBarMain.setOnSeekBarChangeListener(this)
        setDDDifficultiesButtons()

        vb.buttonStartMain.setOnClickListener {
            loadQuestions(vb.tvSeekBarTick.text.toString(), 18, vb.autoCompleteTVDifficulty.text.toString())
        }
    }

    override fun liveData() {
        //loadQuestions(vb.tvSeekBarTick.text.toString(), null, null)
        loadCategories()
    }

    private fun loadQuestions(amountTxt: String, category: Int?, difficulty: String?) {
        viewModel.loadQuestion(amountTxt.toInt(), category, difficulty).observe(this, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    requireContext().toast(resource.data?.response_code.toString())
                    requireContext().toast(resource.data?.results?.get(0)?.question.toString())
                    requireContext().toast(resource.data?.results?.get(0)?.incorrect_answers.toString())
                }
                Status.ERROR -> requireContext().toast(resource.message + resource.code)
                Status.LOADING -> requireContext().toast("Loading")
            }
        })
    }

    private fun loadCategories() {
        viewModel.getCategories().observe(this, { resource ->
            when (resource.status) {
                Status.SUCCESS -> setDDCategoriesButtons(resource)
                Status.ERROR -> requireActivity().toast(resource.message + resource.code)
                Status.LOADING -> requireActivity().toast("Loading")
            }
        })
    }

    private fun setDDCategoriesButtons(resource: Resource<Categories?>) {

        val categoriesList = mutableListOf<String>()
        val categoriesWIndexes = mutableListOf<Int>()
        if (resource.data?.trivia_categories != null) {
            resource.data.trivia_categories.forEachIndexed { index, items ->
                if (items.name != null) {
                    categoriesList.add(items.name)
                    categoriesList.sort()
                    categoriesWIndexes.add(index)
                }
            }
        }
        val categoriesAdapter = ArrayAdapter(requireActivity(), R.layout.dropdown_items, categoriesList)
        vb.autoCompleteTVCategory.setAdapter(categoriesAdapter)
        vb.autoCompleteTVCategory.setDropDownBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.backgr_all
            )
        )
    }

    private fun setDDDifficultiesButtons() {

        val difficulties = resources.getStringArray(R.array.difficulty_array)
        val difficultiesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, difficulties)
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