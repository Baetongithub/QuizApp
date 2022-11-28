package com.geektech.less1quizappkt2.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.databinding.ItemHistoryBinding
import com.geektech.less1quizappkt2.data.model.local.History

class HistoryAdapter(
    private val list: List<History>,
    private val context: Context,
    val onItemClick: (history: History) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.TheViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
         return TheViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false), context)
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener { onItemClick(list[position]) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TheViewHolder(private val itemViewBinding: ItemHistoryBinding, private val context: Context) :
        RecyclerView.ViewHolder(itemViewBinding.root) {

        fun bind(history: History) {

            itemViewBinding.tvCategoryHistory.text =
                String.format(context.getString(R.string.category) + ": " + history.category)

            itemViewBinding.tvCorrectAnswersHistory.text =
                String.format(context.getString(R.string.correct_answers) + ": " + history.correctAnswers)

            itemViewBinding.tvDifficultyHistory.text =
                String.format(context.getString(R.string.difficulty) + ": " + history.difficulty)
        }
    }
}