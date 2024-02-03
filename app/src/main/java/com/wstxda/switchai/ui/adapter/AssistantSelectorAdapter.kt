package com.wstxda.switchai.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wstxda.switchai.R
import com.wstxda.switchai.databinding.ListItemAssistantViewBinding
import com.wstxda.switchai.ui.viewholder.AssistantSelectorItemViewHolder
import com.wstxda.switchai.ui.viewholder.AssistantSelectorCategoryViewHolder
import com.wstxda.switchai.utils.Constants

class AssistantSelectorAdapter(
    private var items: List<AssistantSelectorRecyclerView>,
    private val onAssistantLaunched: (String) -> Unit,
    private val onPinClicked: (String) -> Unit,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(newList: List<AssistantSelectorRecyclerView>) {
        val diff = DiffUtil.calculateDiff(AssistantSelectorDiffCallback(this.items, newList))
        this.items = newList
        diff.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = when (items[position]) {
        is AssistantSelectorRecyclerView.CategoryHeader -> Constants.VIEW_TYPE_CATEGORY_HEADER
        is AssistantSelectorRecyclerView.AssistantSelector -> Constants.VIEW_TYPE_ASSISTANT_ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        Constants.VIEW_TYPE_CATEGORY_HEADER -> {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_assistant_category, parent, false)
            AssistantSelectorCategoryViewHolder(view)
        }

        Constants.VIEW_TYPE_ASSISTANT_ITEM -> {
            val binding = ListItemAssistantViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            AssistantSelectorItemViewHolder(binding)
        }

        else -> throw IllegalArgumentException("Unknown viewType $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        when (val item = items[pos]) {
            is AssistantSelectorRecyclerView.CategoryHeader -> (holder as AssistantSelectorCategoryViewHolder).bind(
                item
            )

            is AssistantSelectorRecyclerView.AssistantSelector -> (holder as AssistantSelectorItemViewHolder).bind(
                item, onAssistantLaunched, onPinClicked
            )
        }
    }

    override fun getItemCount() = items.size
}