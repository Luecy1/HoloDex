package com.example.holodex.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.holodex.databinding.FragmentHololiverDetailBinding
import com.example.holodex.di.ViewModelBuilder
import com.example.holodex.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import javax.inject.Inject

class HololiverDetailFragment : DaggerFragment() {

    lateinit var binding: FragmentHololiverDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HololiverDetailViewModel> { viewModelFactory }

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

        viewModel.streamLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

//        val list = (1..3).map {
//            StreamItem(
//                "title$it",
//                "description",
//                "url"
//            )
//        }
//
//        adapter.submitList(list)

        viewModel.initData()
    }
}


@Module
abstract class HoloLiveDetailViewModelModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun hololiveDetailFragment(): HololiverDetailFragment

    @Binds
    @IntoMap
    @ViewModelKey(HololiverDetailViewModel::class)
    abstract fun bindViewModel(viewModel: HololiverDetailViewModel): ViewModel

}