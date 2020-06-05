package com.example.holodex.data

data class GenerationItem(
    val id: Int,
    val name: String,
    val hololiverList: List<HololiverItem>
)