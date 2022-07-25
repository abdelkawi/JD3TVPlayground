package com.genwin.jd3tv.screens.specials.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.presentation.DotsIndicator
import com.genwin.jd3tv.screens.home.presentation.Header
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

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
            .padding(top = 6.dp)
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
                .padding(top = 6.dp)
        )
        getSpecials().forEach {
            when (it.type) {
                SpecialType.ViewPagerItem -> {
                    ViewPagerItemDesign("", it.gridData)
                }
                SpecialType.HorizontalScrollWithDetailsItem -> {
                    viewPagerItem(it.title, it.gridData, hasDetails = true, isSmall = false)
                }
                SpecialType.HorizontalScrollItem -> {
                    viewPagerItem(it.title, it.gridData, hasDetails = false, isSmall = true)
                }
                SpecialType.ViewPagerWithTitleItem -> {
                    ViewPagerItemDesign(it.title, it.gridData)
                }
                SpecialType.ViewPagerWithDateAndTitleItem -> {
                    ViewPagerItemDesign(it.title, it.gridData)
                }
            }
        }
    }
}

fun getSpecials(): List<Special> {
    val shops = mutableListOf<Special>()
    val grids = mutableListOf<SpecialData>()
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

    val gridsWithoutDate = mutableListOf<SpecialData>()
    gridsWithoutDate.add(SpecialData("Product Name 1", ""))
    gridsWithoutDate.add(SpecialData("Product Name 2", ""))
    gridsWithoutDate.add(SpecialData("Product Name 3", ""))
    gridsWithoutDate.add(SpecialData("Product Name 4", ""))
    gridsWithoutDate.add(SpecialData("Product Name 5", ""))
    gridsWithoutDate.add(SpecialData("Product Name 6", ""))
    gridsWithoutDate.add(SpecialData("Product Name 7", ""))
    gridsWithoutDate.add(SpecialData("Product Name 8", ""))
    gridsWithoutDate.add(SpecialData("Product Name 9", ""))
    gridsWithoutDate.add(SpecialData("Product Name 10", ""))
    shops.add(
        Special(
            "", gridsWithoutDate, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Classes", grids, SpecialType.HorizontalScrollWithDetailsItem
        )
    )
    shops.add(
        Special(
            "Books", grids, SpecialType.HorizontalScrollItem
        )
    )
    shops.add(
        Special(
            "Articles", grids, SpecialType.HorizontalScrollWithDetailsItem
        )
    )
    shops.add(
        Special(
            "Contests", grids, SpecialType.ViewPagerWithDateAndTitleItem
        )
    )
    shops.add(
        Special(
            "Auctions", grids, SpecialType.HorizontalScrollWithDetailsItem
        )
    )
    shops.add(
        Special(
            "Donations", gridsWithoutDate, SpecialType.ViewPagerWithTitleItem
        )
    )
    shops.add(
        Special(
            "Galleries", grids, SpecialType.HorizontalScrollWithDetailsItem
        )
    )
    shops.add(
        Special(
            "Music", gridsWithoutDate, SpecialType.ViewPagerWithTitleItem
        )
    )

    return shops
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerItemDesign(titleStr: String, data: List<SpecialData>) {

    val state = rememberPagerState()
    Surface(
        color = colorResource(id = R.color.dark_jungle_green_50),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        ConstraintLayout {
            val (mainTitle, viewPager, dots) = createRefs()
            if (titleStr.isNotEmpty()) {
                Text(
                    text = titleStr,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    modifier = Modifier.constrainAs(mainTitle) {
                        top.linkTo(parent.top, margin = 9.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    }
                )
            }

            HorizontalPager(
                state = state,
                count = data.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(viewPager) {
                        top.linkTo(mainTitle.bottom, margin = 9.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) { page ->
                val item = data[page]
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 9.dp)
                ) {
                    val (image, description, dateRF, type) = createRefs()
                    AsyncImage(
                        model = "",
                        contentDescription = "",
                        placeholder = painterResource(R.drawable.image_1),
                        error = painterResource(R.drawable.image_1),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .constrainAs(image) {
                                top.linkTo(
                                    parent.top,
                                    margin = if (titleStr.isNotEmpty()) 9.dp else 7.dp
                                )
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(dateRF) {
                            top.linkTo(image.bottom, margin = 23.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                    {
                        if (item.date.isNotEmpty())
                            DateDesign("23", "12", "23")
                    }

                    Text(
                        text = item.title,
                        fontSize = 16.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.constrainAs(description) {
                            top.linkTo(dateRF.bottom, margin = 11.dp)
                            start.linkTo(parent.start)
                            if (item.date.isNotEmpty()) end.linkTo(parent.end)
                        }
                    )
                    Text(
                        text = "Contest",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        modifier = Modifier
                            .constrainAs(type) {
                                bottom.linkTo(image.bottom, margin = 20.dp)
                                end.linkTo(image.end, margin = 20.dp)
                            }
                            .background(Color.Magenta, shape = RoundedCornerShape(5.dp))
                    )
                }
            }
            DotsIndicator(
                totalDots = data.size,
                selectedIndex = state.currentPage,
                modifier = Modifier.constrainAs(dots) {
                    top.linkTo(viewPager.bottom, margin = 24.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 20.dp)
                }, colorResource(id = R.color.indigo2)
            )
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun DateDesign(days: String, hours: String, minutes: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column() {
            Text(
                text = days,
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(
                text = stringResource(id = R.string.day),
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))

            )
        }
        Spacer(modifier = Modifier.padding(start = 19.dp))
        Text(
            text = ":",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))

        )
        Spacer(modifier = Modifier.padding(start = 19.dp))
        Column(verticalArrangement = Arrangement.spacedBy(0.dp)) {
            Text(
                text = hours,
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))

            )
            Text(
                text = stringResource(id = R.string.hour),
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        }
        Spacer(modifier = Modifier.padding(start = 19.dp))
        Text(
            text = ":",
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
        Spacer(modifier = Modifier.padding(start = 19.dp))
        Column() {
            Text(
                text = minutes,
                fontSize = 24.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
            Text(
                text = stringResource(id = R.string.min),
                fontSize = 12.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun viewPagerItem(
    titleStr: String,
    gridData: List<SpecialData>,
    hasDetails: Boolean,
    isSmall: Boolean
) {
    var height = 193.dp
    var width = 193.dp
    if (isSmall) {
        height = 180.dp
        width = 126.dp
    }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 19.dp)
    ) {
        val (title, viewPager) = createRefs()
        Text(
            text = titleStr,
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 16.dp)
            }
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(viewPager) {
                    top.linkTo(title.bottom, margin = 9.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            state = rememberLazyListState()
        ) {
            items(gridData.size) {
                Row() {
                    Spacer(modifier = Modifier.width(10.dp))
                    Column() {
                        AsyncImage(
                            model = "",
                            contentDescription = "",
                            placeholder = painterResource(R.drawable.image_1),
                            error = painterResource(R.drawable.image_1),
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .width(width)
                                .height(height)
                                .clip(RoundedCornerShape(10.dp))
                        )
                        if (hasDetails)
                            Text(
                                text = titleStr,
                                fontSize = 14.sp,
                                color = Color.White,
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                modifier = Modifier.padding(top = 14.dp)
                            )
                    }
                }
            }
        }
    }


}

data class Special(val title: String, val gridData: List<SpecialData>, val type: SpecialType)
data class SpecialData(val title: String, val date: String)
enum class SpecialType {
    ViewPagerItem, HorizontalScrollWithDetailsItem, HorizontalScrollItem, ViewPagerWithTitleItem, ViewPagerWithDateAndTitleItem
}