package com.github.luecy1.holodex.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.luecy1.holodex.EventObserver
import com.github.luecy1.holodex.databinding.FragmentItemListBinding
import com.github.luecy1.holodex.di.ViewModelBuilder
import com.github.luecy1.holodex.di.ViewModelKey
import com.google.android.material.snackbar.Snackbar
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import javax.inject.Inject

class HololiveListFragment : DaggerFragment() {

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

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { resId ->
            Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show()
        })

        viewModel.openHololiver.observe(viewLifecycleOwner, EventObserver {
            val action =
                HololiveListFragmentDirections.actionItemFragmentToHololiverDetailFragment(it)
            findNavController().navigate(action)
        })
    }
}

@Module
abstract class HoloLiveListViewModelModule {

    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    internal abstract fun hololiveListFragment(): HololiveListFragment

    @Binds
    @IntoMap
    @ViewModelKey(HololiveListViewModel::class)
    abstract fun bindViewModel(viewModel: HololiveListViewModel): ViewModel

}
