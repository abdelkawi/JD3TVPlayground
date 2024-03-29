package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.specials.presentation.Special
import com.genwin.jd3tv.screens.specials.presentation.SpecialData
import com.genwin.jd3tv.screens.specials.presentation.SpecialType
import com.genwin.jd3tv.screens.specials.presentation.viewPagerItem

//
// Created by Dina Mounib on 7/23/22.
//
@Composable
fun ShowsMovies(
    categoryType: String,
    sharedPreference: SharedPreference,
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .wrapContentHeight()
    ) {
        CategoryBanner(sharedPreference, navController, categoryType, scaffoldState = scaffoldState)

        getCategories().forEach {
            when (it.type) {
                SpecialType.ViewPagerItem -> {
                    viewPagerItem(it.title, it.gridData, hasDetails = true, isSmall = false)
                }
                SpecialType.ViewPagerWithDateAndTitleItem -> {
                    viewPagerItem(it.title, it.gridData, hasDetails = false, isSmall = true)
                }
                SpecialType.HorizontalScrollWithDetailsItem -> {}
                SpecialType.HorizontalScrollItem -> {}
                SpecialType.ViewPagerWithTitleItem -> {}
            }
        }

    }
}

@Composable
fun CategoryBanner(
    sharedPreference: SharedPreference,
    navController: NavHostController,
    title: String,
    selectedIndex: String = "",
    scaffoldState: ScaffoldState
) {
    ConstraintLayout( modifier = Modifier
        .fillMaxWidth()) {
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
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.cooper_std_black))
            )
            Text(
                text = "Drama * Romance",
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_medium))
            )
            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(R.drawable.play_button),
                    contentDescription = "",
                    Modifier.background(color = colorResource(id = android.R.color.transparent))
                )
            }
        }
        CategoryHeader(
            sharedPreference = sharedPreference,
            navController,
            title, selectedIndex,scaffoldState
        )
    }
}

@Composable
fun CategoryHeader(
    sharedPreference: SharedPreference,
    navController: NavHostController,
    title: String,
    selectedIndex: String,
    scaffoldState:ScaffoldState
) {
    var expanded by remember { mutableStateOf(false) }
    var openCategory by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(selectedIndex.ifEmpty { "Categories" }) }
    Row (modifier = Modifier.fillMaxWidth()){
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (logo, gradient, container) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.top_bar_gradient),
                contentDescription = "",
                modifier = Modifier.constrainAs(gradient) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                })
            Row(modifier = Modifier.fillMaxWidth().padding(top = 6.dp, start = 6.dp, end = 10.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }) {
                Header(
                    sharedPreference = sharedPreference,
                    onClick = { navController.popBackStack() },
                    showBack = true, scaffoldState = scaffoldState
                )
            }

            ConstraintLayout(modifier = Modifier.constrainAs(container) {
                top.linkTo(logo.bottom, 23.dp)
                start.linkTo(parent.start, margin = 16.dp)
            }) {
                val (text1, text2, arrow2) = createRefs()
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    modifier = Modifier.constrainAs(text1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                ClickableText(
                    text = AnnotatedString(
                        selectedCategory.ifEmpty { stringResource(id = R.string.movies) }
                    ),
                    onClick = {
                        expanded = true
                    },
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    modifier = Modifier.constrainAs(text2) {
                        top.linkTo(parent.top)
                        start.linkTo(text1.end, margin = 30.dp)
                    }
                )
                Image(
                    painter = painterResource(R.drawable.ic_arrow_dropdown),
                    contentDescription = "", alignment = Alignment.Center,
                    modifier = Modifier
                        .constrainAs(arrow2) {
                            top.linkTo(text2.top)
                            bottom.linkTo(text2.bottom)
                            start.linkTo(text2.end, margin = 6.dp)
                        }
                        .clickable {
                            expanded = true
                        }
                )
                val categoriesItem = mutableListOf<String>()
                categoriesItem.add("New Shows")
                categoriesItem.add("Podcasts")
                categoriesItem.add("Trending")
                categoriesItem.add("Faith")
                categoriesItem.add("Entertainment")
                categoriesItem.add("Talk Show")
                categoriesItem.add("Self Development")
                categoriesItem.add("Drama")
                categoriesItem.add("Family Matters")
                categoriesItem.add("cooking")
                categoriesItem.add("Business & Finance")
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    categoriesItem.forEach {
                        DropdownMenuItem(onClick = {
                            selectedCategory = it
                            expanded = false
                            openCategory = true
                        }) {
                            Text(
                                it,
                                color = colorResource(id = R.color.smoky_black),
                                fontSize = 16.sp,
                                fontFamily = FontFamily(Font(R.font.poppins_regular))
                            )
                        }
                        if (categoriesItem[categoriesItem.lastIndex] != it)
                            Divider(
                                color = colorResource(id = R.color.davyGray),
                                thickness = 1.dp
                            )

                    }
                }

                if (openCategory) {
                    navController.navigate("category/$title/$selectedCategory")
                    openCategory = false
                }
            }
        }
    }

}

fun getCategories(): List<Special> {
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
            "New Shows", gridsWithoutDate, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Podcasts", grids, SpecialType.ViewPagerWithDateAndTitleItem
        )
    )
    shops.add(
        Special(
            "Trending", grids, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Faith", grids, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Entertainment", grids, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Talk Show", grids, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Self Development", gridsWithoutDate, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Drama", grids, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Family Matters", gridsWithoutDate, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "cooking", gridsWithoutDate, SpecialType.ViewPagerItem
        )
    )
    shops.add(
        Special(
            "Business & Finance", gridsWithoutDate, SpecialType.ViewPagerItem
        )
    )

    return shops
}