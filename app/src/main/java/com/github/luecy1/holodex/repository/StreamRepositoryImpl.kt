package com.github.luecy1.holodex.repository

import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.detail.StreamItem
import com.github.luecy1.holodex.repository.api.StreamInfoService
import com.github.luecy1.holodex.repository.api.xml.Feed
import javax.inject.Inject

class StreamRepositoryImpl @Inject constructor(
    private val streamInfoService: StreamInfoService
) : StreamRepository {

    override suspend fun getStreamInfoList(channelId: String): Result<List<StreamItem>> {
        val result = streamInfoService.fetchStreamInfo(channelId)
        when (result) {
            is Result.Success<Feed> -> {
                val streamList = result.data.entryList.map { entry ->
                    StreamItem(
                        entry.title,
                        entry.mediaGroup.description.take(100),
                        entry.mediaGroup.thumbnail
                    )
                }
                return Result.Success(streamList)
            }
            is Result.Error -> {
                return result
            }
        }
    }
}
