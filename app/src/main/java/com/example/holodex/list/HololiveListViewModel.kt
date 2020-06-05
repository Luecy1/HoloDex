package com.example.holodex.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.holodex.repository.HoloLiveRepository
import javax.inject.Inject

class HololiveListViewModel @Inject constructor(
    private val repository: HoloLiveRepository
) : ViewModel() {

    val hololiveList = liveData {
        emit(repository.getHoloLiveList())
    }
}