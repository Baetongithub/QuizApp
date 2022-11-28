package com.geektech.less1quizappkt2.ui.main

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


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate), SeekBar.OnSeekBarChangeListener {

    private val viewModel: ViewModel by viewModel()
    private val ccs: CheckConnectionState by lazy { CheckConnectionState(requireActivity().application) }
    private var isConnected = false
    private val categoriesList = mutableListOf<String>()
//  private lateinit var seekBarAmountTxt: String

    override fun checkConnectionNetworkState() = ccs.observe(this) { isConnected = it }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        vb.tvSeekBarTick.text = progress.toString()
    }

    override fun setUpUI() {
        vb.seekBarMain.setOnSeekBarChangeListener(this)
        setDDDifficultiesButtons()

        // start button - check difficulties & categ-s before start
        vb.buttonStartMain.setOnClickListener {
            when {
                // check diff-s wasn't selected
                vb.autoCompleteTVDifficulty.text.toString() == context?.getString(R.string.any_difficulty) -> loadQuestions(
                    vb.tvSeekBarTick.text.toString(),
                    categoriesList.indexOf(vb.autoCompleteTVCategory.text.toString()) + 9,
                    null
                )
                //check categ-s wasn't selected
                //decapitalize - decapitlizes strings if theres cap letters
                vb.autoCompleteTVCategory.text.toString() == context?.getString(R.string.all_categories) -> loadQuestions(
                    vb.tvSeekBarTick.text.toString(),
                    null,
                    vb.autoCompleteTVDifficulty.text.toString().replaceFirstChar { it.lowercase() }
                )
                else -> loadQuestions(
                    vb.tvSeekBarTick.text.toString(),
                    categoriesList.indexOf(vb.autoCompleteTVCategory.text.toString()) + 9,
                    vb.autoCompleteTVDifficulty.text.toString().replaceFirstChar { it.lowercase() }
                )
            }
            toast((categoriesList.indexOf(vb.autoCompleteTVCategory.text.toString()) + 9).toString())

        }
    }

    override fun liveData() {
        //loadQuestions(vb.tvSeekBarTick.text.toString(), null, null)
        loadCategories()
    }

    private fun loadQuestions(amountTxt: String, category: Int?, difficulty: String?) {
        viewModel.loadQuestion(amountTxt.toInt(), category, difficulty).observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    requireContext().toast(resource.data?.response_code.toString())
                    requireContext().toast(resource.data?.results?.get(0)?.question.toString())
                    requireContext().toast(resource.data?.results?.get(0)?.incorrect_answers.toString())
                }
                Status.ERROR -> requireContext().toast(resource.message + resource.code)
                Status.LOADING -> requireContext().toast("Loading")
            }
        }
    }

    private fun loadCategories() {
        viewModel.getCategories().observe(this) { resource ->
            when (resource.status) {
                Status.SUCCESS -> setDDCategoriesButton(resource)
                Status.ERROR -> requireActivity().toast(resource.message + resource.code)
                Status.LOADING -> requireActivity().toast("Loading")
            }
        }
    }

    private fun setDDCategoriesButton(resource: Resource<Categories?>) {

        val categoriesWIndexes = mutableListOf<Int>()
        if (resource.data?.trivia_categories != null) {
            categoriesList.add(context?.getString(R.string.all_categories).toString())
            resource.data.trivia_categories.forEachIndexed { index, items ->
                if (items.name != null) {
                    categoriesWIndexes.add(index)
                    categoriesList.add(items.name)
                    //categoriesList.sort()
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