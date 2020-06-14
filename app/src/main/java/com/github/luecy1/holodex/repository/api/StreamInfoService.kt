package com.github.luecy1.holodex.repository.api

import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.api.xml.Feed

interface StreamInfoService {
    suspend fun fetchStreamInfo(channelId: String): Result<Feed>
}
