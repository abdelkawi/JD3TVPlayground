package com.genwin.jd3tv.screens.events.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.genwin.jd3tv.screens.home.data.Fundraiser
import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.data.ItemDetailsResponse
import com.genwin.jd3tv.screens.home.data.MainPhoto
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType
import com.genwin.jd3tv.screens.home.presentation.DotsIndicator
import com.genwin.jd3tv.screens.home.presentation.Header
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

//
// Created by Dina Mounib on 7/19/22.
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Event(sharedPreference: SharedPreference) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        item(span = {
            GridItemSpan(2)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, start = 16.dp, end = 16.dp)
            ) { Header(sharedPreference = sharedPreference, onClick = {}) }
        }
        item(span = {
            GridItemSpan(2)
        }) {
            Text(
                text = stringResource(id = R.string.event), fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semibold)
                ),
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        item(span = {
            GridItemSpan(2)
        }) {
            val homeSec = HomeSection(
                "test1", SectionType.CardWithTitle, "",
                ItemDetailsRequest(null, "", "", emptyList())
            )
            val ll = mutableListOf<ItemDetailsResponse>()
            ll.add(
                ItemDetailsResponse(
                    experienceId = "",
                    title = "test1",
                    mainPhoto = MainPhoto(""),
                    summary = "",
                    fundraiser = Fundraiser(""),
                    id = "",
                    organization = null,
                    alias = "",
                    createdAt = ""
                )
            )
            ll.add(
                ItemDetailsResponse(
                    experienceId = "",
                    title = "test2",
                    mainPhoto = MainPhoto(""),
                    summary = "",
                    fundraiser = Fundraiser(""),
                    id = "",
                    organization = null,
                    alias = "",
                    createdAt = ""
                )
            )
            ll.add(
                ItemDetailsResponse(
                    experienceId = "",
                    title = "test3",
                    mainPhoto = MainPhoto(""),
                    summary = "",
                    fundraiser = Fundraiser(""),
                    id = "",
                    organization = null,
                    alias = "",
                    createdAt = ""
                )
            )
            homeSec.setItems(ll)
            eventViewPagerWithDots(homeSec)
        }
        item(span = {
            GridItemSpan(2)
        }) {
            Text(
                text = stringResource(id = R.string.event_calender),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 19.dp, start = 25.dp, end = 25.dp)
            )
        }
        item(span={GridItemSpan(2)})
        {
            EventRow("","16 - 18 Jun 2022", "The 6th frequency","","16 - 18 Jun 2022", "The 6th frequency")
        }
//        items(2) {
//            EventItem(
//                "",
//                "16 - 18 Jun 2022",
//                "The 6th frequency"
//            )
//        }
        item(span = {
            GridItemSpan(2)
        }) {
            Text(
                text = stringResource(id = R.string.previous_calender),
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 19.dp, start = 25.dp, end = 25.dp)

            )
        }
        item(span={GridItemSpan(2)})
        {
            EventRow("","16 - 18 Jun 2022", "The 6th frequency","","16 - 18 Jun 2022", "The 6th frequency")
        }
//        items(2) {
//            EventItem(
//                "",
//                "16 - 18 Jun 2022",
//                "The 6th frequency"
//            )
//        }
    }
}

@Composable
fun EventRow(
    itemUrl: String, dateStr: String, titleStr: String,
    itemUrl2: String, dateStr2: String, titleStr2: String
) {
    Row(modifier = Modifier.fillMaxWidth().padding(start = 25.dp,end=25.dp))
    {
        Box(modifier = Modifier.weight(0.5f).padding(end = 10.dp))
        {
            EventItem(itemUrl, dateStr, titleStr)
        }
        Box(modifier = Modifier.weight(0.5f))
        {
            EventItem(itemUrl2, dateStr2, titleStr2)
        }

    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
fun EventItem(
    itemUrl: String, dateStr: String, titleStr: String
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (img1, title1, date1) = createRefs()
        AsyncImage(
            model = itemUrl,
            contentDescription = "",
            placeholder = painterResource(R.drawable.image_1),
            error = painterResource(R.drawable.image_1),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(246.dp)
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(img1) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
        Text(
            text = dateStr,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            modifier = Modifier.constrainAs(date1) {
                top.linkTo(img1.bottom, margin = 10.dp)
                start.linkTo(img1.start)

            },style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )

        Text(
            text = titleStr,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            modifier = Modifier.constrainAs(title1) {
                top.linkTo(date1.bottom, margin = 2.dp)
                start.linkTo(img1.start)

            },style = TextStyle(platformStyle = PlatformTextStyle(includeFontPadding = false))
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun eventViewPagerWithDots(section: HomeSection) {
    val state = rememberPagerState()
    Surface(
        color = colorResource(id = R.color.dark_jungle_green_50)
    ) {
        ConstraintLayout {
            val (viewPager, dots, topSpacer, bottomSpacer) = createRefs()
            HorizontalPager(
                state = state,
                count = section.getItems().size,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .constrainAs(viewPager) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) { page ->
                val item = section.getItems()[page]
                ConstraintLayout(modifier = Modifier.padding(8.dp)) {
                    val (image, title, type) = createRefs()
                    AsyncImage(
                        model = item.mainPhoto?.fileUrl ?: "",
                        contentDescription = "",
                        placeholder = painterResource(R.drawable.image_1),
                        error = painterResource(R.drawable.image_1),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .constrainAs(image) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })

                    Text(
                        text = item.title ?: "This is title ",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_regular)),
                        modifier = Modifier.constrainAs(title) {
                            top.linkTo(image.bottom, margin = 16.dp)
                            start.linkTo(image.start)
                        }
                    )
                    Text(
                        text = "Live",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        modifier = Modifier
                            .constrainAs(type) {
                                bottom.linkTo(image.bottom, margin = 16.dp)
                                end.linkTo(image.end, margin = 16.dp)
                            }
                            .background(
                                colorResource(id = R.color.deep_magenta),
                                shape = RoundedCornerShape(5.dp)
                            )
                    )
                }
            }
            Spacer(modifier = Modifier
                .padding(4.dp)
                .constrainAs(topSpacer) { top.linkTo(viewPager.bottom) })
            DotsIndicator(
                totalDots = section.getItems().size,
                selectedIndex = state.currentPage,
                modifier = Modifier.constrainAs(dots) {
                    top.linkTo(topSpacer.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, colorResource(R.color.indigo2)
            )
            Spacer(modifier = Modifier
                .padding(4.dp)
                .constrainAs(bottomSpacer) { top.linkTo(dots.bottom) })
        }
    }
}