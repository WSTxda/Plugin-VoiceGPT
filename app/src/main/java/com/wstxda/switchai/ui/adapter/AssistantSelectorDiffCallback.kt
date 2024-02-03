package com.wstxda.switchai.ui.adapter

import androidx.recyclerview.widget.DiffUtil

class AssistantSelectorDiffCallback(
    private val oldList: List<AssistantSelectorRecyclerView>,
    private val newList: List<AssistantSelectorRecyclerView>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldPos: Int, newPos: Int) = when {
        oldList[oldPos] is AssistantSelectorRecyclerView.AssistantSelector && newList[newPos] is AssistantSelectorRecyclerView.AssistantSelector -> (oldList[oldPos] as AssistantSelectorRecyclerView.AssistantSelector).assistantItem.key == (newList[newPos] as AssistantSelectorRecyclerView.AssistantSelector).assistantItem.key

        oldList[oldPos] is AssistantSelectorRecyclerView.CategoryHeader && newList[newPos] is AssistantSelectorRecyclerView.CategoryHeader -> (oldList[oldPos] as AssistantSelectorRecyclerView.CategoryHeader).title == (newList[newPos] as AssistantSelectorRecyclerView.CategoryHeader).title

        else -> false
    }

    override fun areContentsTheSame(oldPos: Int, newPos: Int) = oldList[oldPos] == newList[newPos]
}