package com.github.luecy1.holodex.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.github.luecy1.holodex.App
import com.github.luecy1.holodex.databinding.FragmentHololiverDetailBinding
import dagger.android.support.DaggerFragment

class HololiverDetailFragment : DaggerFragment() {

    lateinit var binding: FragmentHololiverDetailBinding

    private val viewModel by viewModels<HololiverDetailViewModel> {
        (activity?.application as App).appComponent
            .detailComponent()
            .create(args.hololiverItem)
            .viewModelFactory()
    }

    private val args: HololiverDetailFragmentArgs by navArgs()

    private val hololiver by lazy { args.hololiverItem }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHololiverDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.hololiver = hololiver

        viewModel.streamLiveData.observe(viewLifecycleOwner, Observer {
            val streamInfoAdapter = StreamInfoAdapter(requireContext())
            streamInfoAdapter.submitList(it)

            binding.streamInfoDetail.adapter = streamInfoAdapter
        })

        viewModel.fanArtLiveData.observe(viewLifecycleOwner, Observer { fanArtList ->
            val fanArtAdapter = FanArtAdapter()
            fanArtAdapter.submitList(fanArtList)

            binding.fanArtList.adapter = fanArtAdapter
        })

        viewModel.initData()
    }
}