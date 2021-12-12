package com.github.luecy1.holodex

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContent {

        }
    }
}

@Composable
fun LiverItem() {
    val roundedCornerSize = 24.dp

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lamy),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = roundedCornerSize, topEnd = roundedCornerSize))
                .fillMaxWidth()
        )
        Text(
            text = "雪花ラミィ",
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

val Colors.textColor: Color
    get() = if (isLight) Color.Black else Color.White

val Colors.textBackgroundColor: Color
    get() = if (isLight) Color.LightGray else Color(0xea, 0x80, 0xfc)


@Preview(showBackground = false)
@Composable
fun hoge() {
    HoloDexTheme {
        LiverItem()
    }
}

@Preview(showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun hoge2() {
    HoloDexTheme(darkTheme = true) {
        LiverItem()
    }
}

@Composable
fun HoloDexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors()
    } else {
        lightColors()
    }

    MaterialTheme(
        colors = colors,
        typography = Typography(
            h6 = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.textColor
            )
        ),
        shapes = Shapes(),
        content = content
    )
}