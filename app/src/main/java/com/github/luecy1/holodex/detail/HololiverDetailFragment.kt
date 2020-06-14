package com.github.luecy1.holodex.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.github.luecy1.holodex.databinding.FragmentHololiverDetailBinding
import com.github.luecy1.holodex.di.ViewModelBuilder
import com.github.luecy1.holodex.di.ViewModelKey
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
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.image.load("https://pbs.twimg.com/profile_images/1263309015661965313/E34lYRNA_400x400.jpg")

        val streamInfoAdapter = StreamInfoAdapter(requireContext())
        binding.streamInfoDetail.adapter = streamInfoAdapter

        viewModel.streamLiveData.observe(viewLifecycleOwner, Observer {
            streamInfoAdapter.submitList(it)
        })

        val fanArtAdapter = FanArtAdapter()
        binding.fanArtList.adapter = fanArtAdapter

        viewModel.fanArtLiveData.observe(viewLifecycleOwner, Observer { fanArtList ->
            fanArtAdapter.submitList(fanArtList)
        })

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
