package com.github.luecy1.holodex.ui.top

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.github.luecy1.holodex.R
import com.github.luecy1.holodex.data.HololiverItem
import com.github.luecy1.holodex.preview_data.lamyData
import com.github.luecy1.holodex.style.HoloDexTheme
import com.github.luecy1.holodex.style.textBackgroundColor
import com.github.luecy1.holodex.style.textColor

@ExperimentalCoilApi
@Composable
fun LiverItem(
    liver: HololiverItem = lamyData,
    onClick: (HololiverItem) -> Unit,
) {
    val roundedCornerSize = 10.dp

    val imagePainter = if (ImagePreview.current) {
        painterResource(id = R.drawable.lamy)
    } else {
        rememberImagePainter(liver.imageUrl)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .width(200.dp)
            .clickable {
                onClick(liver)
            }
    ) {
        Image(
            painter = imagePainter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = roundedCornerSize, topEnd = roundedCornerSize))
                .size(200.dp)
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


@ExperimentalCoilApi
@Preview(showBackground = false)
@Composable
fun LiverItemPreview() {
    HoloDexTheme {
        LiverItem(lamyData) {
        }
    }
}

@ExperimentalCoilApi
@Preview(showBackground = false, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LiverItemPreviewDark() {
    HoloDexTheme(darkTheme = true) {
        LiverItem(lamyData) {
        }
    }
}

val ImagePreview = compositionLocalOf {
    true
}