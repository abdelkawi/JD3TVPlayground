package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

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

@Composable
fun Banner(
) {
    ConstraintLayout() {
        val (image, dataContainer, gradient) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.movie_poster_),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(id = R.drawable.ic_gradient_transparent),
            contentDescription = "",
            modifier = Modifier.constrainAs(gradient) {
                start.linkTo(parent.start)
                bottom.linkTo(image.bottom)
                end.linkTo(parent.end)
            }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(dataContainer) {
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Walk Of Fame",
                fontSize = 50.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentWidth()
                    .clickable { },
                fontFamily = FontFamily(Font(R.font.cooper_std_black))
            )
            Text(
                text = "Drama * Romance",
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(R.drawable.play_button),
                    contentDescription = "",
                    Modifier.background(color = colorResource(id = android.R.color.transparent))
                )

            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Contest(section: HomeSection) {
    Surface(
        color = Color(R.color.dark_jungle_green)
    ) {
        Column {
            getSectionTitle(title = section.title)
            HorizontalPager(
                count = 10,//section.getItems().size,
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .fillMaxWidth()
            ) { page ->
                // Our page content
                Column {
                    AsyncImage(
                        model = "",
//                    section.getItems()[page].mainPhoto?.fileUrl ?: "",
                        placeholder = painterResource(R.drawable.image_1),
                        contentDescription = null,
                        error = painterResource(R.drawable.image_1),
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(223.dp)
                            .clip(RoundedCornerShape(10.dp))
                    )
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "",//section.getItems()[page].title ?: "",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
            }
        }
    }
}


@Composable
fun CardsSection(section: HomeSection) {
    Column {
        getSectionTitle(section.title)
        LazyRow(modifier = Modifier.padding(start = 16.dp)) {
            items(10) {//section.getItems()) {
                AsyncImage(
                    model = "",//it.mainPhoto?.fileUrl ?: "",
                    placeholder = painterResource(R.drawable.image_1),
                    contentDescription = null,
                    error = painterResource(R.drawable.image_1),
                    modifier = Modifier
                        .height(180.dp)
                        .width(126.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }

}

@Composable
fun CardsWithTitleSection(section: HomeSection) {
    getSectionTitle(section.title)
    LazyRow(modifier = Modifier.padding(start = 16.dp)) {
        items(10) {//section.getItems()) { item ->
            Column {
                AsyncImage(
                    model = "",//item.mainPhoto?.fileUrl ?: "",
                    placeholder = painterResource(R.drawable.image_1),
                    contentDescription = null,
                    error = painterResource(R.drawable.image_1),
                    modifier = Modifier
                        .height(180.dp)
                        .width(126.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "test",//item.title ?: "",
                    modifier = Modifier.padding(top = 14.dp),
                    color = Color.White,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun HostSection(section: HomeSection) {
    getSectionTitle(section.title)
    LazyRow(modifier = Modifier.padding(start = 16.dp)) {
        items(10) {//section.getItems()) { item ->
            AsyncImage(
                model = "",//item.mainPhoto?.fileUrl ?: "",
                placeholder = painterResource(R.drawable.image_1),
                contentDescription = null,
                error = painterResource(R.drawable.image_1),
                modifier = Modifier
                    .height(126.dp)
                    .padding(end = 10.dp)
                    .width(126.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ShopSection(section: HomeSection) {
    getSectionTitle(section.title)
    LazyRow {
        items(10) {//section.getItems()) { item ->
            AsyncImage(
                model = "",//item.mainPhoto?.fileUrl ?: "",
                placeholder = painterResource(R.drawable.ic_product),
                contentDescription = null,
                error = painterResource(R.drawable.ic_product),
                modifier = Modifier
                    .height(126.dp)
                    .padding(end = 10.dp)
                    .width(126.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(color = Color.White),
                contentScale = ContentScale.Inside
            )
        }
    }
}

@Composable
fun homeFaithViewPagerItem(
    titleStr: String
) {
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
            items(10) {
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
                                .width(126.dp)
                                .height(180.dp)
                                .clip(RoundedCornerShape(10.dp))
                        )
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun viewPagerWithDots(section: HomeSection) {
    val state = rememberPagerState()
    Surface(
        color = colorResource(id = R.color.dark_jungle_green_50)
    ) {
        Column {
            getSectionTitle(section.title, 9)
            ConstraintLayout {
                val (viewPager, dots, topSpacer, bottomSpacer) = createRefs()
                HorizontalPager(
                    state = state,
                    count = 10,//section.getItems().size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .constrainAs(viewPager) {
                            top.linkTo(parent.top, margin = 9.dp)
                            start.linkTo(parent.start, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                ) { page ->
//                val item = section.getItems()[page]
                    ConstraintLayout(modifier = Modifier.padding(horizontal = 16.dp)) {
                        val (image, title, type) = createRefs()
                        AsyncImage(
                            model = "",//item.mainPhoto?.fileUrl ?: "",
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
                            text = "This is title ",//item.title ?:,
                            fontSize = 16.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.poppins_regular)),
                            modifier = Modifier.constrainAs(title) {
                                top.linkTo(image.bottom, margin = 16.dp)
                            }
                        )
                        Text(
                            text = "Contest".uppercase(),
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .constrainAs(type) {
                                    bottom.linkTo(image.bottom, margin = 16.dp)
                                    end.linkTo(image.end, margin = 16.dp)
                                }
                                .width(80.dp)
                                .height(28.dp)
                                .padding(2.dp)
                                .background(Color.Magenta, shape = RoundedCornerShape(6.dp))
                        )
                    }
                }
                Spacer(modifier = Modifier
                    .constrainAs(topSpacer) { top.linkTo(viewPager.bottom) })
                DotsIndicator(
                    totalDots = 10,// section.getItems().size,
                    selectedIndex = state.currentPage,
                    modifier = Modifier.constrainAs(dots) {
                        top.linkTo(topSpacer.bottom, margin = 26.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }, Color(0xFFe225ff)
                )
                Spacer(modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(bottomSpacer) { top.linkTo(dots.bottom) })
            }
        }
    }
}

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    modifier: Modifier,
    backGroundColor: Color
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .width(20.dp)
                        .height(6.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(backGroundColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(CircleShape)
                        .background(color = Color.White)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }

}

