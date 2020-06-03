package com.example.holodex.repository.api.data

data class HololiveItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val generation: List<String>
)