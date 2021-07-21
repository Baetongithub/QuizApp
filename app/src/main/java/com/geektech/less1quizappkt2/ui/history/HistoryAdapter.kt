package com.geektech.less1quizappkt2.ui.history

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.databinding.ItemHistoryBinding
import com.geektech.less1quizappkt2.extensions.inflate
import com.geektech.less1quizappkt2.data.model.room.History

class HistoryAdapter(
    private val list: List<History>,
    private val context: Context,
    val onItemClick: (history: History) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.TheViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
        val view = parent.inflate(R.layout.item_history)
        return TheViewHolder(view, context)
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        holder.bind(list[position])

        holder.itemView.setOnClickListener { onItemClick(list[position]) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class TheViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private var itemViewBinding = ItemHistoryBinding.bind(itemView)

        fun bind(history: History) {

            itemViewBinding.tvCategoryHistory.text =
                (context.getString(R.string.category) + ": " + history.category)

            itemViewBinding.tvCorrectAnswersHistory.text =
                (context.getString(R.string.correct_answers) + ": " + history.correctAnswers)

            itemViewBinding.tvDifficultyHistory.text =
                (context.getString(R.string.difficulty) + ": " + history.difficulty)
        }
    }
}