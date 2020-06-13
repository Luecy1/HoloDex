package com.example.holodex.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("imageURL")
fun ImageView.imageURI(uri: String) {
    this.load(uri)
}

@BindingAdapter("link")
fun TextView.addlink(link: String) {
    TODO()
}