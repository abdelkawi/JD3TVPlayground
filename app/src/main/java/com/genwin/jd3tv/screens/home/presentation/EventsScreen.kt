package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.screens.home.data.Fundraiser
import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.data.ItemDetailsResponse
import com.genwin.jd3tv.screens.home.data.MainPhoto
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

//
// Created by Dina Mounib on 7/19/22.
//
@Composable
fun Event() {
    Surface(
        color = Color.Black,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (logo, title, viewPager, newEventsTxt, previousEventsTxt, newEventsList, previousEventsList) = createRefs()
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
                    text = stringResource(id = R.string.event), fontSize = 24.sp,
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

                Box(modifier = Modifier
                    .constrainAs(viewPager) {
                        top.linkTo(title.bottom, margin = 22.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(colorResource(id = R.color.dark_jungle_green_50))) {
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

                Text(text = stringResource(id = R.string.event_calender),
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontSize = 20.sp,
                    modifier = Modifier.constrainAs(newEventsTxt) {
                        top.linkTo(viewPager.bottom, margin = 29.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    })
                Box(modifier = Modifier
                    .constrainAs(newEventsList) {
                        top.linkTo(newEventsTxt.bottom, margin = 9.dp)
                        start.linkTo(parent.start, margin = 37.dp)
                        end.linkTo(parent.end)
                    }
                    .background(colorResource(id = R.color.black))
                )
                {
                    EventItem(
                        "",
                        "16 - 18 Jun 2022",
                        "The 6th frequency",
                        "",
                        "16 - 18 Jun 2022",
                        "The 6th frequency"
                    )
                }

                Text(text = stringResource(id = R.string.previous_calender),
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontSize = 20.sp,
                    modifier = Modifier.constrainAs(previousEventsTxt) {
                        top.linkTo(newEventsList.bottom, margin = 29.dp)
                        start.linkTo(parent.start, margin = 16.dp)
                    })
                Box(modifier = Modifier
                    .constrainAs(previousEventsList) {
                        top.linkTo(previousEventsTxt.bottom, margin = 9.dp)
                        start.linkTo(parent.start, margin = 37.dp)
                        end.linkTo(parent.end)
                    }
                    .background(colorResource(id = R.color.black))
                ) {
                    EventItem(
                        "",
                        "16 - 18 Jun 2022",
                        "The 6th frequency",
                        "",
                        "16 - 18 Jun 2022",
                        "The 6th frequency"
                    )
                }
            }
        }
    }
}

@Composable
fun EventItem(
    itemUrl: String, dateStr: String, titleStr: String,
    itemUrl2: String, dateStr2: String, titleStr2: String
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (img2, title2, date2) = createRefs()


        val (img1, title1, date1) = createRefs()
        AsyncImage(
            model = itemUrl,
            contentDescription = "",
            placeholder = painterResource(R.drawable.image_1),
            error = painterResource(R.drawable.image_1),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(246.dp)
                .width(152.dp)
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

            })

        Text(
            text = titleStr,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            modifier = Modifier.constrainAs(title1) {
                top.linkTo(date1.bottom, margin = 2.dp)
                start.linkTo(img1.start)

            })



        AsyncImage(
            model = itemUrl2,
            contentDescription = "",
            placeholder = painterResource(R.drawable.image_1),
            error = painterResource(R.drawable.image_1),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(246.dp)
                .width(152.dp)
                .clip(RoundedCornerShape(10.dp))
                .constrainAs(img2) {
                    top.linkTo(parent.top)
                    start.linkTo(img1.end, margin = 16.dp)
                })

        Text(
            text = dateStr2,
            color = Color.White,
            fontSize = 12.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            modifier = Modifier.constrainAs(date2) {
                top.linkTo(img2.bottom, margin = 10.dp)
                start.linkTo(img2.start)

            })

        Text(
            text = titleStr2,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            modifier = Modifier.constrainAs(title2) {
                top.linkTo(date2.bottom, margin = 2.dp)
                start.linkTo(img2.start)
                end.linkTo(img2.end, margin = 16.dp)
            })

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
                            .background(colorResource(id = R.color.deep_magenta), shape = RoundedCornerShape(5.dp))
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