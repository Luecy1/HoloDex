package com.example.holodex.repository.api

import com.example.holodex.repository.preference.PreferenceService
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

    override suspend fun searchStatusWithImage(): List<Status> = withContext(Dispatchers.IO) {
        checkFirstAccess()

        val queryResult = twitter.search(Query("#かなたーと -filter:retweets"))
        queryResult.tweets
    }

    private fun checkFirstAccess() {
        if (firstFlg.not()) return

        val oAuth2Token = twitter.oAuth2Token
        preferenceService.setString("accessToken", oAuth2Token.accessToken)

        firstFlg = false
    }
}