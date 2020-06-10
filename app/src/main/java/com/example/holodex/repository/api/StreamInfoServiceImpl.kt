package com.example.holodex.repository.api

import com.example.holodex.data.Result
import com.example.holodex.repository.api.xml.Feed
import com.example.holodex.repository.api.xml.StreamInfoParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class StreamInfoServiceImpl @Inject constructor(
    private val client: OkHttpClient
) : StreamInfoService {

    private val parser = StreamInfoParser()

    override suspend fun fetchStreamInfo(channelId: String): Result<Feed> =
        withContext(Dispatchers.IO) {

            val urlBuilder = "https://www.youtube.com/feeds/videos.xml".toHttpUrlOrNull()?.let {
                it.newBuilder()
            } ?: throw IllegalStateException(" ")

            val url = urlBuilder
                .addQueryParameter("channel_id", channelId)
                .build()

            val request = Request.Builder()
                .url(url)
                .get()
                .build()

            val response = client.newCall(request).execute()

            val inputStream = response.body?.byteStream()!!

            val feedList = parser.parse(inputStream)

            Result.Success(feedList)
        }
}