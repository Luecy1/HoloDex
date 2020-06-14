package com.github.luecy1.holodex.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

@BindingAdapter("imageURL")
fun ImageView.imageURI(uri: String) {
    if (uri.isNotBlank()) {
        this.load(uri)
    }
}

//@BindingAdapter("link")
//fun TextView.addlink(link: String) {
//    TODO()
//}
