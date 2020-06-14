package com.github.luecy1.holodex.repository.preference

interface PreferenceService {

    fun getString(key: String): String

    fun setString(key: String, value: String)
}
