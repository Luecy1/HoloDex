package com.github.luecy1.holodex.style

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

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