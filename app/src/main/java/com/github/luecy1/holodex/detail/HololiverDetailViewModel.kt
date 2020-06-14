package com.github.luecy1.holodex.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.StreamRepository
import com.github.luecy1.holodex.repository.api.TwitterService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import twitter4j.Status
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

            val streamInfoDeferred = async { streamRepository.getStreamInfoList() }
            val statusListDeferred = async { twitterService.searchStatusWithImage() }

            when (val streamInfoResult = streamInfoDeferred.await()) {
                is Result.Success<List<StreamItem>> -> {
                    _streamLiveData.postValue(streamInfoResult.data)
                }
            }

            _streamLoading.postValue(false)

            when (val statusListResult = statusListDeferred.await()) {
                is Result.Success<List<Status>> -> {
                    val fanArtList = statusListResult.data.map {
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
    }
}
