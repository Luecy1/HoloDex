package com.example.holodex.repository.api

import com.example.holodex.data.Result
import com.example.holodex.repository.api.xml.Feed

interface StreamInfoService {

    //    https://www.youtube.com/feeds/videos.xml?channel_id=UCZlDXzGoo7d44bwdNObFacg
    suspend fun fetchStreamInfo(channelId: String): Result<Feed>
}