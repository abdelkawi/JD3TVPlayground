package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference

//
// Created by Dina Mounib on 7/23/22.
//
@Composable
fun Category(
    categoryType: String,
    sharedPreference: SharedPreference,
    homeNavController: NavHostController
) {
    CategoryBanner(sharedPreference, homeNavController, categoryType)
}

@Composable
fun CategoryBanner(
    sharedPreference: SharedPreference,
    homeNavController: NavHostController,
    title: String
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
                    painter = painterResource(R.drawable.ic_play_button),
                    contentDescription = "",
                    Modifier.background(color = colorResource(id = android.R.color.transparent))
                )
                Text(
                    text = "Play",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold))
                )
            }
        }
        CategoryHeader(sharedPreference = sharedPreference, title)
    }
}

@Composable
fun CategoryHeader(
    sharedPreference: SharedPreference,
    title: String
) {
    var expanded by remember { mutableStateOf(false) }
    Row {
        ConstraintLayout() {
            val (logo, profileImg, gradient, container) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.top_bar_gradient),
                contentDescription = "",
                modifier = Modifier.constrainAs(gradient) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                })
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

            if (sharedPreference.getPhoto().isNotEmpty())
                AsyncImage(
                    model = sharedPreference.getPhoto(),
                    contentDescription = null,
                    modifier = Modifier
                        .clip(CircleShape)
                        .width(28.dp)
                        .height(28.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .constrainAs(profileImg) {
                            top.linkTo(parent.top, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                ) else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(colorResource(id = R.color.persian_blue), shape = CircleShape)
                        .height(28.dp)
                        .width(28.dp)
                        .border(1.dp, Color.White, CircleShape)
                        .constrainAs(profileImg) {
                            top.linkTo(parent.top, margin = 16.dp)
                            end.linkTo(parent.end, margin = 16.dp)
                        }
                ) {
                    Text(
                        text = sharedPreference.getNickName(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                    )
                }
            }

            ConstraintLayout(modifier = Modifier.constrainAs(container) {
                top.linkTo(logo.bottom, 26.dp)
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
                    text = AnnotatedString(stringResource(id = R.string.movies)),
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
                        .clickable { expanded = true }
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
                        DropdownMenuItem(onClick = { expanded = false }) {
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
            }
        }
    }

}