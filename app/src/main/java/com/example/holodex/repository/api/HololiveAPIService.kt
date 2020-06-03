package com.example.holodex.repository.api

import com.example.holodex.repository.api.data.HololiveItem
import retrofit2.http.GET

interface HololiveAPIService {

    @GET("/TwitterAPI/members.json")
    suspend fun hololiveMembers(): List<HololiveItem>
}