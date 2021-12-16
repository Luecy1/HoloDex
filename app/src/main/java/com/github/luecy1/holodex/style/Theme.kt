package com.github.luecy1.holodex.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
        colors = colors.copy(
            primary = Color(0x80, 0xDB, 0xF6)
        ),
        typography = Typography(
            h6 = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.textColor
            )
        ),
        shapes = Shapes(),
        content = content
    )
}