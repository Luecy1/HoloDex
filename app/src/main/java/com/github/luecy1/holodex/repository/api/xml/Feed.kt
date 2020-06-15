package com.github.luecy1.holodex.repository.api.xml

data class Feed(val entryList: List<Entry>)

data class Entry(val title: String, val mediaGroup: MediaGroup, val link: String?)

data class MediaGroup(val title: String, val thumbnail: String, val description: String)
