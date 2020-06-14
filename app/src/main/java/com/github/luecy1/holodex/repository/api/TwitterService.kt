package com.github.luecy1.holodex.repository.api

import com.github.luecy1.holodex.data.Result
import twitter4j.Status

interface TwitterService {
    suspend fun searchStatusWithImage(): Result<List<Status>>
}
