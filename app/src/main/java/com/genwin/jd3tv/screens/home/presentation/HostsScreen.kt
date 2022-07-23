package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference

//
// Created by Dina Mounib on 7/20/22.
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Host(hostSize: Int, titleStr: String, sharedPreference: SharedPreference) {
    val numbers = (0..10).toList()
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        item(span = {
            GridItemSpan(2)
        }) { Header(sharedPreference = sharedPreference) }
        item(span = {
            GridItemSpan(2)
        }) {
            Text(
                text = titleStr, fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semibold)
                ),
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        items(numbers.size) {
            Column {
                HostCell("", "Host 1")
            }
        }
    }

}

@Composable
fun HostCell(image: String, hostName: String) {
    Column() {//horizontalAlignment = Layout.Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.ic_host), contentDescription = "",
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = hostName,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )

    }

}