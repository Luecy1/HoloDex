package com.github.luecy1.holodex.repository.api

import com.github.luecy1.holodex.data.Result
import com.github.luecy1.holodex.repository.preference.PreferenceService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import twitter4j.Query
import twitter4j.Status
import twitter4j.Twitter

class TwitterServiceImpl(
    private val twitter: Twitter,
    private val preferenceService: PreferenceService
) : TwitterService {

    var firstFlg = true

    override suspend fun searchStatusWithImage(fanArtHashTag: String): Result<List<Status>> =
        withContext(Dispatchers.IO) {
            try {

                if (fanArtHashTag.isEmpty()) {
                    return@withContext Result.Error(Exception(" non hash tag ...? "))
                }

                checkFirstAccess()

                val queryResult =
                    twitter.search(Query("$fanArtHashTag -filter:retweets filter:images"))
                Result.Success(queryResult.tweets)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    private fun checkFirstAccess() {
        if (firstFlg.not()) return

        val oAuth2Token = twitter.oAuth2Token
        preferenceService.setString("accessToken", oAuth2Token.accessToken)

        firstFlg = false
    }
}
