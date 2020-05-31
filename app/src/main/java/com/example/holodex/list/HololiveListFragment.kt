package com.example.holodex.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.holodex.databinding.FragmentItemListBinding
import com.example.holodex.di.ViewModelBuilder
import com.example.holodex.di.ViewModelKey
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
//        val adapter = MyItemRecyclerViewAdapter(view.context)
//
//        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.addItemDecoration(GridDecoration(20))
//
//        viewModel.hololiveLiat.observe(viewLifecycleOwner, Observer { hololiveList ->
//            adapter.submitList(hololiveList)
//        })

        val adapter = GenerationItemAdapter(view.context)

        binding.recyclerView.layoutManager = LinearLayoutManager(view.context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }

        binding.recyclerView.adapter = adapter

        viewModel.hololiveLiat2.observe(viewLifecycleOwner, Observer { generationList ->
            adapter.submitList(generationList)
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