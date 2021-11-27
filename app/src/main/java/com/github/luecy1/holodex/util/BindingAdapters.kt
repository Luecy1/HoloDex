package com.github.luecy1.holodex.util

import android.os.Build
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import java.net.URLEncoder


@BindingAdapter("imageURL")
fun ImageView.imageURI(uri: String) {
    if (uri.isNotBlank()) {
        this.load(uri)
    }
}

@BindingAdapter("baseUrl", "href", "innerText")
fun TextView.link(baseUrl: Int, href: String, innerText: String) {

    val encode = URLEncoder.encode(href, "UTF-8")
    val link = context.resources.getString(baseUrl, encode)

    this.anchorLink(link, innerText)
}

@BindingAdapter("url", "innerText")
fun TextView.noQueryLink(url: String, innerText: String) {
    this.anchorLink(url, innerText)
}

private fun TextView.anchorLink(link: String, text: String) {

    val html = "<a href=\"$link\">$text</a>"

    val spanned: Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(html)
    }

    this.movementMethod = LinkMovementMethod.getInstance()
    this.text = spanned
}