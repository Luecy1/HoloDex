package com.github.luecy1.holodex.style

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Colors.textColor: Color
    get() = if (isLight) Color.Black else Color.White

val Colors.textBackgroundColor: Color
    get() = if (isLight) Color(0xea, 0x80, 0xfc) else Color(0x8f, 0x17, 0x91)
