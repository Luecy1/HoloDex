package com.github.luecy1.holodex.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luecy1.holodex.Event
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.GenerationItem
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.HoloLiveRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HololiveListViewModel @Inject constructor(
    private val repository: HoloLiveRepository
) : ViewModel() {

    private val _openHololiver = MutableLiveData<Event<HololiverItem>>()
    val openHololiver: LiveData<Event<HololiverItem>> = _openHololiver

    private val _errorMessage = MutableLiveData<Event<Int>>()
    val errorMessage: LiveData<Event<Int>> = _errorMessage

    private val _hololiveList = MutableLiveData<List<GenerationItem>>()
    val hololiveList: LiveData<List<GenerationItem>> = _hololiveList

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    init {
        initData()
    }

    fun initData(forceLoad: Boolean = false) {

        viewModelScope.launch {
            _loading.value = true

            when (val holoLiveListResult = repository.getHoloLiveList(forceLoad)) {
                is Result.Success<List<GenerationItem>> -> {
                }
                is Result.Error -> {
                    _hololiveList.value = emptyList()
                    _errorMessage.value = Event(R.string.error_message)
                }
            }
            _loading.value = false
        }
    }

    fun onClickHololiver(hololiverItem: HololiverItem) {
        _openHololiver.postValue(Event(hololiverItem))
    }
}
