package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference

//
// Created by Dina Mounib on 7/23/22.
//
@Composable
fun SpecialsScreen(sharedPreference: SharedPreference) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .wrapContentHeight()
    ) {
        Header(sharedPreference = sharedPreference)
        Text(
            text = stringResource(id = R.string.special), fontSize = 24.sp,
            fontFamily = FontFamily(
                Font(R.font.poppins_semibold)
            ),
            color = Color.White,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
        getSpecials().forEach {
            when (it.type) {
                SpecialType.ViewPagerItem -> {

                }
                SpecialType.HorizontalScrollWithTitleItem -> {

                }
                SpecialType.HorizontalScrollItem -> {

                }
                SpecialType.ViewPagerWithDateItem -> {

                }
            }
        }
    }
}

fun getSpecials(): List<Special> {
    var shops = mutableListOf<Special>()
    var grids = mutableListOf<SpecialData>()
    grids.add(SpecialData("Product Name 1", "Price 1"))
    grids.add(SpecialData("Product Name 2", "Price 2"))
    grids.add(SpecialData("Product Name 3", "Price 3"))
    grids.add(SpecialData("Product Name 4", "Price 4"))
    grids.add(SpecialData("Product Name 5", "Price 5"))
    grids.add(SpecialData("Product Name 6", "Price 6"))
    grids.add(SpecialData("Product Name 7", "Price 7"))
    grids.add(SpecialData("Product Name 8", "Price 8"))
    grids.add(SpecialData("Product Name 9", "Price 9"))
    grids.add(SpecialData("Product Name 10", "Price 10"))
    shops.add(
        Special(
            "Enter to win a life time experience with \n" +
                    "Kimberly Lechnick", grids, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "", grids, SpecialType.HorizontalScrollItem
        )
    )
    shops.add(
        Special(
            "", grids, SpecialType.HorizontalScrollWithTitleItem
        )
    )
    shops.add(
        Special(
            "", grids, SpecialType.ViewPagerWithDateItem
        )
    )
    return shops
}

data class Special(val title: String, val gridData: List<SpecialData>, val type: SpecialType)
data class SpecialData(val title: String, val date: String)
enum class SpecialType {
    ViewPagerItem, HorizontalScrollWithTitleItem, HorizontalScrollItem, ViewPagerWithDateItem
}