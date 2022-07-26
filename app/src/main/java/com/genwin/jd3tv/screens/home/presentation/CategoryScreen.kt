package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference

//
// Created by Dina Mounib on 7/26/22.
//
@Composable
fun CategoryScreen(
    categoryType: String,
    sharedPreference: SharedPreference,
    navController: NavHostController,
    selectedCategory:String
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .wrapContentHeight()
    ) {
        CategoryBanner(sharedPreference = sharedPreference, navController, categoryType,selectedCategory)
        CategoryRow()
        CategoryRow()
        CategoryRow()
    }

}

@Composable
fun CategoryRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 14.dp, start = 20.dp, end = 20.dp)
    ) {
        AsyncImage(
            model = "",
            contentDescription = "",
            placeholder = painterResource(R.drawable.image_1),
            error = painterResource(R.drawable.image_1),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(0.5f)
                .height(246.dp)
                .padding(end = 5.dp)
                .clip(RoundedCornerShape(6.dp))
        )

        AsyncImage(
            model = "",
            contentDescription = "",
            placeholder = painterResource(R.drawable.image_1),
            error = painterResource(R.drawable.image_1),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .weight(0.5f)
                .height(246.dp)
                .padding(start = 5.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    }
}