package com.example.holodex.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.holodex.MyItemRecyclerViewAdapter
import com.example.holodex.R
import com.example.holodex.di.ViewModelBuilder
import com.example.holodex.di.ViewModelKey
import com.example.holodex.list.DummyContent.DummyItem
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.DaggerFragment
import dagger.multibindings.IntoMap
import javax.inject.Inject

class HololiveListFragment : DaggerFragment() {

    private var columnCount = 2

    private var listener: OnListFragmentInteractionListener? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<HololiveListViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(
                    DummyContent.ITEMS,
                    listener
                )
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            HololiveListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
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