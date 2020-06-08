package com.example.holodex.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HololiverDetailViewModel : ViewModel() {

    private val _streamLiveData = MutableLiveData<List<StreamItem>>()
    val streamLiveData: LiveData<List<StreamItem>> = _streamLiveData

    fun initData() {

    }
}