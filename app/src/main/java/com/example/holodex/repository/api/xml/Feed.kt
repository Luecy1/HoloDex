package com.example.holodex.repository.api.xml

data class Feed(
    val entryList: List<Entry>
)

data class Entry(
    val title: String
)