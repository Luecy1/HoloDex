package com.example.holodex.list

import androidx.lifecycle.ViewModel
import com.example.holodex.repository.HoloLiverRepository
import javax.inject.Inject

class HololiveListViewModel @Inject constructor(
    val repository: HoloLiverRepository
) : ViewModel() {

}