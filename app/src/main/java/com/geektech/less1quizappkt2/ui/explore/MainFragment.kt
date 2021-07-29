package com.geektech.less1quizappkt2.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.base.BaseFragment
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

        setUpDropDownButtons()

//        vb.buttonStartMain.setOnClickListener {
//            getData(vb.tvSeekBarTick.text.toString().toInt(), 18, vb.autoCompleteTVDifficulty.text.toString())
//        }
    }

    override fun liveData() {
        getData(vb.tvSeekBarTick.text.toString(), null, null)
    }

    private fun getData(amountTxt: String, category: Int?, difficulty: String?) {
        viewModel.loadQuestion(amountTxt.toInt(), category, difficulty).observe(this, { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    resource.data?.results?.get(0)?.category?.forEach {
                        val categories = mutableListOf(it)
                        val categoriesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, categories)
                        vb.autoCompleteTVCategory.setAdapter(categoriesAdapter)
                    }
                    vb.autoCompleteTVCategory.setDropDownBackgroundDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.backgr_all
                        )
                    )
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

    private fun setUpDropDownButtons() {
//        val categories = resources.getStringArray(R.array.category_array)
//        val categoriesAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_items, categories)
//        vb.autoCompleteTVCategory.setAdapter(categoriesAdapter)
//        vb.autoCompleteTVCategory.setDropDownBackgroundDrawable(
//            ContextCompat.getDrawable(
//                requireContext(),
//                R.drawable.backgr_all
//            )
//        )

        val difficulties = resources.getStringArray(R.array.difficulty_array)
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