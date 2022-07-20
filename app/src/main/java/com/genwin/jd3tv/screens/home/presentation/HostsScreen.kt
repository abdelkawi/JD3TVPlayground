package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

//
// Created by Dina Mounib on 7/20/22.
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Host(hostSize: Int, titleStr: String) {
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (logo, title, gridView) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.ic_jd_tv_logo),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                alignment = Alignment.TopStart,
                modifier = Modifier.constrainAs(logo) {
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )
            Text(
                text = titleStr, fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semibold)
                ),
                color = Color.White,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(logo.bottom, margin = 7.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            val numbers = (0..hostSize).toList()
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .constrainAs(gridView) {
                        top.linkTo(title.bottom, margin = 22.dp)
                        start.linkTo(parent.start, margin = 36.dp)
                        end.linkTo(parent.end, margin = 36.dp)
                    }
            ) {
                items(numbers.size) {
                    Column {
                        HostCell("", "Host 1")
                    }
                }
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