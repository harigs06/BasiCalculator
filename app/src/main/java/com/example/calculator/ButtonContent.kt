package com.example.calculator

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class ButtonContent(

    val text : String ,
    val color : Color,
    var onClick : () -> Unit
)
