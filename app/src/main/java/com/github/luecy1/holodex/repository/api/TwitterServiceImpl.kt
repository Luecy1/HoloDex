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

    override suspend fun searchStatusWithImage(): Result<List<Status>> =
        withContext(Dispatchers.IO) {
            try {
                checkFirstAccess()

                val queryResult = twitter.search(Query("#かなたーと -filter:retweets filter:images"))
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
