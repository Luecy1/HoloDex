package com.github.luecy1.holodex.ui.top

import androidx.lifecycle.ViewModel
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.repository.HoloLiveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HololiveDetailViewModel @Inject constructor(
    val repository: HoloLiveRepository
) : ViewModel() {

    fun findHoloLiver(id: String): HololiverItem {
        return repository.findHoloLiver(id = id)
    }
}