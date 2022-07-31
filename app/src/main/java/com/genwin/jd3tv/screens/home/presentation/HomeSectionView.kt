package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.genwin.jd3tv.R

@Composable
fun getSectionTitle(title: String, topPadding: Int = 19) {
    Text(
        text = title,
        color = Color.White,
        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
        fontSize = 20.sp,
        modifier = Modifier.padding(top = topPadding.dp, start = 16.dp)
    )
}
