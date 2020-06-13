package com.example.holodex.detail

import androidx.lifecycle.*
import com.example.holodex.data.Result
import com.example.holodex.repository.StreamRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HololiverDetailViewModel @Inject constructor(
    private val repository: StreamRepository
) : ViewModel() {

    private val _streamLiveData = MutableLiveData<List<StreamItem>>()
    val streamLiveData: LiveData<List<StreamItem>> = _streamLiveData

    private val _streamLoading = MutableLiveData(false)
    val streamLoading: LiveData<Boolean> = _streamLoading

    val fanartLiveData: LiveData<List<FanArtItem>> = liveData {

        val artList = (1..3).map {
            FanArtItem(
                0L,
                "",
                "",
                "",
                "body $it",
                ""
            )
        }

        emit(artList)
    }

    fun initData() {
        viewModelScope.launch {
            _streamLoading.postValue(true)

            val result = repository.getStreamInfoList()

            when (result) {
                is Result.Success<List<StreamItem>> -> {
                    _streamLiveData.postValue(result.data)
                }
            }

            _streamLoading.postValue(false)
        }
    }
}