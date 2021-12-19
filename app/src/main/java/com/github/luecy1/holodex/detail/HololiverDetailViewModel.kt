package com.github.luecy1.holodex.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luecy1.holodex.Event
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.StreamRepository
import com.github.luecy1.holodex.repository.api.TwitterService
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import twitter4j.Status
import javax.inject.Inject

class HololiverDetailViewModel @Inject constructor(
    private val hololiverItem: HololiverItem,
    private val streamRepository: StreamRepository,
    private val twitterService: TwitterService
) : ViewModel() {

    private val _streamLiveData = MutableLiveData<List<StreamItem>>()
    val streamLiveData: LiveData<List<StreamItem>> = _streamLiveData

    private val _streamLoading = MutableLiveData(false)
    val streamLoading: LiveData<Boolean> = _streamLoading

    private val _fanArtLiveData = MutableLiveData<List<FanArtItem>>()
    val fanArtLiveData: LiveData<List<FanArtItem>> = _fanArtLiveData

    private val _onStreamInfoClick = MutableLiveData<Event<StreamItem>>()
    val onStreamInfoClick: LiveData<Event<StreamItem>> = _onStreamInfoClick

    private val _errorMessage = MutableLiveData<Event<Int>>()
    val errorMessage: LiveData<Event<Int>> = _errorMessage

    fun initData() {
        viewModelScope.launch {
            _streamLoading.postValue(true)

            val streamInfoDeferred =
                async { streamRepository.getStreamInfoList(hololiverItem.channelId) }
            val statusListDeferred =
                async { twitterService.searchStatusWithImage(hololiverItem.fanArtHashTag) }

            when (val streamInfoResult = streamInfoDeferred.await()) {
                is Result.Success<List<StreamItem>> -> {
                }
                is Result.Error -> {
                    _errorMessage.value = Event(R.string.error_message)
                }
            }

            _streamLoading.postValue(false)

            when (val statusListResult = statusListDeferred.await()) {
                is Result.Success<List<Status>> -> {
                    val fanArtList = statusListResult.data.map {
                        val user = it.user

                        val mediaList = it.mediaEntities.map { media ->
                            media.mediaURLHttps
                        }

                        FanArtItem(
                            it.id,
                            user.profileImageURLHttps,
                            user.name,
                            user.screenName,
                            it.text,
                            mediaList,
                            "https://twitter.com/${it.user.screenName}/status/${it.id}"
                        )
                    }
                    _fanArtLiveData.postValue(fanArtList)
                }
                is Result.Error -> {
                    _errorMessage.value = Event(R.string.error_message)
                }
            }
        }
    }

    fun onStreamItemClick(streamItem: StreamItem) {
        _onStreamInfoClick.value = Event(streamItem)
    }
}
