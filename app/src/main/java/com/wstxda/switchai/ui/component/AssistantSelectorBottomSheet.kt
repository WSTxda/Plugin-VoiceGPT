package com.wstxda.switchai.ui.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wstxda.switchai.databinding.FragmentAssistantDialogBinding
import com.wstxda.switchai.ui.adapter.AssistantSelectorAdapter
import com.wstxda.switchai.viewmodel.AssistantSelectorViewModel

class AssistantSelectorBottomSheet : BottomSheetDialogFragment() {
    private var _binding: FragmentAssistantDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AssistantSelectorViewModel by viewModels()

    private lateinit var assistantSelectorAdapter: AssistantSelectorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAssistantDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        assistantSelectorAdapter = AssistantSelectorAdapter(emptyList(), onAssistantLaunched = { assistantKey ->
            viewModel.assistantLaunched(assistantKey)
            dismiss()
        }, onPinClicked = { assistantKey ->
            viewModel.togglePinAssistant(assistantKey)
        })

        binding.assistantsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = assistantSelectorAdapter
        }

        viewModel.assistantItems.observe(viewLifecycleOwner) { items ->
            assistantSelectorAdapter.updateList(items)
        }

        binding.assistantsRecyclerView.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val lm = recyclerView.layoutManager as? LinearLayoutManager ?: return
                val first = lm.findFirstCompletelyVisibleItemPosition()
                val last = lm.findLastCompletelyVisibleItemPosition()
                val total = assistantSelectorAdapter.itemCount
                binding.dividerTop.isVisible = first > 0
                binding.dividerBottom.isVisible = last < total - 1
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}