package com.github.luecy1.holodex.repository.api

import com.github.luecy1.holodex.repository.api.data.HololiveItem
import retrofit2.http.GET

interface HololiveAPIService {

    @GET("members.json")
    suspend fun hololiveMembers(): List<HololiveItem>
}
