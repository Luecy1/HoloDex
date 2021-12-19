package com.github.luecy1.holodex.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.luecy1.holodex.EventObserver
import com.github.luecy1.holodex.databinding.FragmentItemListBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.analytics.FirebaseAnalytics
import javax.inject.Inject

class HololiveListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HololiveListViewModel> { viewModelFactory }

    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        val adapter = GenerationItemAdapter(view.context, viewModel)

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        binding.recyclerView.adapter = adapter

        viewModel.hololiveList.observe(viewLifecycleOwner, Observer { generationList ->
            adapter.submitList(generationList)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, EventObserver { resId ->
            Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.openHololiver.observe(viewLifecycleOwner, EventObserver {

            val loggingBundle = Bundle().apply {
                putString("id", it.id.toString())
                putString("name", it.name)
            }

            // event log
            FirebaseAnalytics.getInstance(requireContext()).logEvent(
                FirebaseAnalytics.Event.SELECT_CONTENT, loggingBundle
            )

        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.initData(forceLoad = true)
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }
}

