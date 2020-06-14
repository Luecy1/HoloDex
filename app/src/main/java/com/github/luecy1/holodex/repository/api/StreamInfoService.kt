package com.github.luecy1.holodex.repository.api

import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.api.xml.Feed

interface StreamInfoService {

    //    https://www.youtube.com/feeds/videos.xml?channel_id=UCZlDXzGoo7d44bwdNObFacg
    suspend fun fetchStreamInfo(channelId: String): Result<Feed>
}
