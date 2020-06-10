package com.example.holodex.repository

import com.example.holodex.data.Result
import com.example.holodex.detail.StreamItem
import com.example.holodex.repository.api.StreamInfoService
import com.example.holodex.repository.api.xml.Feed
import javax.inject.Inject

class StreamRepositoryImpl @Inject constructor(
    private val streamInfoService: StreamInfoService
) : StreamRepository {

    override suspend fun getStreamInfoList(): Result<List<StreamItem>> {
        val result = streamInfoService.fetchStreamInfo("UCZlDXzGoo7d44bwdNObFacg")
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