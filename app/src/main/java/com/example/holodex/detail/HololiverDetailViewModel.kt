package com.example.holodex.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holodex.data.Result
import com.example.holodex.repository.StreamRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HololiverDetailViewModel @Inject constructor(
    private val repository: StreamRepository
) : ViewModel() {

    private val _streamLiveData = MutableLiveData<List<StreamItem>>()
    val streamLiveData: LiveData<List<StreamItem>> = _streamLiveData

    fun initData() {
        viewModelScope.launch {
            val result = repository.getStreamInfoList()

            when (result) {
                is Result.Success<List<StreamItem>> -> {
                    _streamLiveData.postValue(result.data)
                }
            }
        }
    }
}