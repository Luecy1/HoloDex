package com.github.luecy1.holodex.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.StreamRepository
import com.github.luecy1.holodex.repository.api.TwitterService
import kotlinx.coroutines.launch
import javax.inject.Inject

class HololiverDetailViewModel @Inject constructor(
    private val streamRepository: StreamRepository,
    private val twitterService: TwitterService
) : ViewModel() {

    private val _streamLiveData = MutableLiveData<List<StreamItem>>()
    val streamLiveData: LiveData<List<StreamItem>> = _streamLiveData

    private val _streamLoading = MutableLiveData(false)
    val streamLoading: LiveData<Boolean> = _streamLoading

    private val _fanArtLiveData = MutableLiveData<List<FanArtItem>>()
    val fanArtLiveData: LiveData<List<FanArtItem>> = _fanArtLiveData

    fun initData() {
        viewModelScope.launch {
            _streamLoading.postValue(true)

            val result = streamRepository.getStreamInfoList()

            when (result) {
                is Result.Success<List<StreamItem>> -> {
                    _streamLiveData.postValue(result.data)
                }
            }

            _streamLoading.postValue(false)

            val fanArtList = twitterService.searchStatusWithImage().map {
                val user = it.user
                val media = it.mediaEntities.firstOrNull()
                FanArtItem(
                    it.id,
                    user.profileImageURLHttps,
                    user.name,
                    user.screenName,
                    it.text,
                    media?.mediaURLHttps ?: ""
                )
            }

            _fanArtLiveData.postValue(fanArtList)
        }
    }
}
