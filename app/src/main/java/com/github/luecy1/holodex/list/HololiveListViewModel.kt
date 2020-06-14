package com.github.luecy1.holodex.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.github.luecy1.holodex.Event
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.repository.HoloLiveRepository
import javax.inject.Inject

class HololiveListViewModel @Inject constructor(
    private val repository: HoloLiveRepository
) : ViewModel() {

    private val _openHololiver = MutableLiveData<Event<String>>()
    val openHololiver: LiveData<Event<String>> = _openHololiver

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    val hololiveList = liveData {
        emit(repository.getHoloLiveList())
    }

    fun onClickHololiver(hololiverItem: HololiverItem) {
        _openHololiver.postValue(Event(hololiverItem.id.toString()))
    }
}
