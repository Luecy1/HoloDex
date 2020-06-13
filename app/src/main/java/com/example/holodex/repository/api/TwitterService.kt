package com.example.holodex.repository.api

import twitter4j.Status

interface TwitterService {
    suspend fun searchStatusWithImage(): List<Status>
}