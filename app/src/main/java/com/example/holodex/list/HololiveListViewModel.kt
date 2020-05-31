package com.example.holodex.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.holodex.repository.HoloLiverRepository
import javax.inject.Inject

class HololiveListViewModel @Inject constructor(
    private val repository: HoloLiverRepository
) : ViewModel() {

    val hololiveLiat = liveData {
        emit(repository.getHoloLiveList())
    }

    val hololiveLiat2 = liveData {
        emit(repository.getHoloLiveList2())
    }
}