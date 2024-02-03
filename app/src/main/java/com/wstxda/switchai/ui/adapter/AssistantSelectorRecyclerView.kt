package com.wstxda.switchai.ui.adapter

import com.wstxda.switchai.viewmodel.AssistantSelectorViewModel

sealed interface AssistantSelectorRecyclerView {
    data class CategoryHeader(val title: String) : AssistantSelectorRecyclerView
    data class AssistantSelector(val assistantItem: AssistantSelectorViewModel.AssistantItem) :
        AssistantSelectorRecyclerView
}