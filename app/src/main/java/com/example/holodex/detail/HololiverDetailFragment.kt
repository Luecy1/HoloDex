package com.example.holodex.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.holodex.databinding.FragmentHololiverDetailBinding
import com.example.holodex.repository.api.xml.StreamInfoParser
import timber.log.Timber

class HololiverDetailFragment : Fragment() {

    lateinit var binding: FragmentHololiverDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHololiverDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StreamInfoAdapter(requireContext())
        binding.streamInfoDetail.adapter = adapter

        val list = (1..3).map {
            StreamItem(
                "title$it",
                "description",
                "url"
            )
        }

        adapter.submitList(list)


        for (entry in StreamInfoParser().parse()) {
            Timber.d(entry.toString())
        }
    }
}