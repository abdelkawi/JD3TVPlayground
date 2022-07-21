package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

//
// Created by Dina Mounib on 7/21/22.
//

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Shop(sharedPreference: SharedPreference) {
    var shops = mutableListOf<ShopData>()
    var grids = mutableListOf<GridData>()
    grids.add(GridData("Product Name 1", "Price 1"))
    grids.add(GridData("Product Name 2", "Price 2"))
    grids.add(GridData("Product Name 3", "Price 3"))
    grids.add(GridData("Product Name 4", "Price 4"))
    grids.add(GridData("Product Name 5", "Price 5"))
    grids.add(GridData("Product Name 6", "Price 6"))
    grids.add(GridData("Product Name 7", "Price 7"))
    grids.add(GridData("Product Name 8", "Price 8"))
    grids.add(GridData("Product Name 9", "Price 9"))
    grids.add(GridData("Product Name 10", "Price 10"))
    shops.add(
        ShopData(
            "Enter to win a life time experience with \n" +
                    "Kimberly Lechnick", emptyList(), ShopType.Card
        )
    )
    shops.add(
        ShopData(
            "", emptyList(), ShopType.Collection
        )
    )
    shops.add(
        ShopData(
            "", grids, ShopType.Grid
        )
    )
    val numbers = (0..10).toList()
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        item(span = {
            GridItemSpan(2)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) { Header(sharedPreference = sharedPreference) }
        }
        item(span = {
            GridItemSpan(2)
        }) {
            Text(
                text = stringResource(id = R.string.shop), fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semibold)
                ),
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        shops.forEach {
            when (it.type) {
                ShopType.Card -> {
                    item(span = {
                        GridItemSpan(2)
                    }) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = colorResource(id = R.color.dark_jungle_green_50))
                        ) {
                            CardShop(it.title)
                        }
                    }
                }
                ShopType.Collection -> {
                    item(span = {
                        GridItemSpan(2)
                    }) { CollectionShop(10) }
                }
                ShopType.Grid -> {
                    items(numbers.size) {
                        Column {
                            GridItemShop()
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun CardShop(cardTitle: String) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (image, text) = createRefs()
        Image(
            painter = painterResource(R.drawable.ic_host), contentDescription = "",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(image) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
                .fillMaxWidth()
        )
        Text(
            text = cardTitle,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 16.sp,
            color = Color.White,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(text) {
                    start.linkTo(parent.start)
                    top.linkTo(image.bottom, margin = 14.dp)
                    bottom.linkTo(parent.bottom, margin = 52.dp)
                }
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CollectionShop(size: Int) {
    ConstraintLayout {
        val state = rememberPagerState()
        val (title, viewPager, dots) = createRefs()
        Text(
            text = "New Collection",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            modifier = Modifier.constrainAs(title) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            })
        HorizontalPager(
            state = state,
            count = size,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .constrainAs(viewPager) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) { page ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ConstraintLayout(modifier = Modifier.weight(0.5f)) {
                    ViewPagerItem()
                }

                ConstraintLayout(modifier = Modifier.weight(0.5f)) {
                    ViewPagerItem()
                }
            }

        }

        DotsIndicator(
            totalDots = if (size % 2 == 0) size.div(2) else size.div(2).plus(1),
            selectedIndex = state.currentPage,
            modifier = Modifier.constrainAs(dots) {
                top.linkTo(viewPager.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }, colorResource(R.color.indigo2)
        )
    }
}

@Composable
fun ViewPagerItem() {
    ConstraintLayout(modifier = Modifier.padding(8.dp)) {
        val (image, title, type) = createRefs()
        Image(
            painter = painterResource(R.drawable.ic_host), contentDescription = "",
            contentScale = ContentScale.Inside,
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
            text = "Product name ",
            fontSize = 16.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            modifier = Modifier.constrainAs(title) {
                top.linkTo(image.bottom, margin = 7.dp)
                start.linkTo(image.start)
            }
        )
        Text(
            text = "Price",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            modifier = Modifier
                .constrainAs(type) {
                    top.linkTo(title.bottom, margin = 3.dp)
                }

        )
    }
}

@Composable
fun GridItemShop() {
    Column(modifier = Modifier.background(color = Color.Black)) {//horizontalAlignment = Layout.Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.ic_host), contentDescription = "",
            contentScale = ContentScale.Inside
        )
        Spacer(modifier = Modifier.height(13.dp))
        Text(
            text = "Product Name",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Price",
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )

    }
}

data class ShopData(val title: String, val gridData: List<GridData>, val type: ShopType)
data class GridData(val title: String, val price: String)
enum class ShopType {
    Card, Collection, Grid
}