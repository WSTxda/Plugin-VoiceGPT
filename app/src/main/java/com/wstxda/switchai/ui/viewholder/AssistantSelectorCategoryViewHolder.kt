package com.wstxda.switchai.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wstxda.switchai.R
import com.wstxda.switchai.ui.adapter.AssistantSelectorRecyclerView

class AssistantSelectorCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val titleTextView: TextView = itemView.findViewById(R.id.category_title_text_view)
    fun bind(categoryHeader: AssistantSelectorRecyclerView.CategoryHeader) {
        titleTextView.text = categoryHeader.title
    }
}