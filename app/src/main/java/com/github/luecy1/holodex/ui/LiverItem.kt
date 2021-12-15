package com.github.luecy1.holodex.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.style.HoloDexTheme
import com.github.luecy1.holodex.style.textBackgroundColor
import com.github.luecy1.holodex.style.textColor

@ExperimentalCoilApi
@Composable
fun LiverItem(
    imagePainter: Painter? = null,  // for preview
    liver: HololiverItem = lamyData,
) {
    val roundedCornerSize = 10.dp

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(200.dp)
    ) {
        Image(
            painter = imagePainter ?: rememberImagePainter(liver.imageUrl),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = roundedCornerSize, topEnd = roundedCornerSize))
                .fillMaxWidth()
        )
        Text(
            text = liver.name,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h6.copy(color = MaterialTheme.colors.textColor),
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        bottomStart = roundedCornerSize,
                        bottomEnd = roundedCornerSize
                    )
                )
                .background(MaterialTheme.colors.textBackgroundColor)
        )
    }
}

private val lamyData = HololiverItem(
    id = 0,
    name = "雪花ラミィ",
    twitterScreenName = "",
    imageUrl = "https://pbs.twimg.com/profile_images/1469238330067877889/imRH03bc_400x400.jpg",
    generation = listOf(),
    basicInfo = "",
    channelId = "",
    fanArtHashTag = "",
)

@ExperimentalCoilApi
@Preview(showBackground = false)
@Composable
fun LiverItemPreview() {
    HoloDexTheme {
        LiverItem(painterResource(id = R.drawable.lamy), lamyData)
    }
}

@ExperimentalCoilApi
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LiverItemPreviewDark() {
    HoloDexTheme(darkTheme = true) {
        LiverItem(painterResource(id = R.drawable.lamy), lamyData)
    }
}