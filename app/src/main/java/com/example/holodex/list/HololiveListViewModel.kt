package com.example.holodex.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.holodex.Event
import com.example.holodex.data.HololiverItem
import com.example.holodex.repository.HoloLiveRepository
import javax.inject.Inject

class HololiveListViewModel @Inject constructor(
    private val repository: HoloLiveRepository
) : ViewModel() {

    private val _openHololiver = MutableLiveData<Event<String>>()
    val openHololiver: LiveData<Event<String>> = _openHololiver

    val hololiveList = liveData {
        emit(repository.getHoloLiveList())
    }

    fun onClickHololiver(hololiverItem: HololiverItem) {
        _openHololiver.postValue(Event(hololiverItem.id.toString()))
    }
}