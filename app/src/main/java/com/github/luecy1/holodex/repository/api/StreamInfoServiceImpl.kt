package com.github.luecy1.holodex.repository.api

import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.api.xml.Feed
import com.github.luecy1.holodex.repository.api.xml.StreamInfoParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import javax.inject.Inject

class StreamInfoServiceImpl @Inject constructor(
    private val client: OkHttpClient
) : StreamInfoService {

    private val parser = StreamInfoParser()

    override suspend fun fetchStreamInfo(channelId: String): Result<Feed> =
        withContext(Dispatchers.IO) {

            try {
                val urlBase = "https://www.youtube.com/feeds/videos.xml"
                val urlBuilder = urlBase.toHttpUrlOrNull()?.newBuilder()
                    ?: throw IllegalStateException("parse error $urlBase")

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
            } catch (e: Exception) {
                Timber.w(e)
                Result.Error(e)
            }
        }
}
