package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference
import java.time.format.TextStyle

//
// Created by Dina Mounib on 7/21/22.
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Search(sharedPreference: SharedPreference) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        cells = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(13.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        item(span = {
            GridItemSpan(2)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) { SearchHeader(sharedPreference = sharedPreference) }
        }
        item(span = {
            GridItemSpan(2)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                searchBar()
            }
        }
        item(span = {
            GridItemSpan(2)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Search results for 'movie'",
                    color = colorResource(id = R.color.white),
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_regular))
                )
            }
        }
        items(count = 10) {
            Column {
                HostCell("", "Host 1")
            }
        }
    }
}

@Composable
fun searchBar() {
    var text = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    TextField(
        value = text.value,
        onValueChange = {
            text.value = it
        },
        label = null,
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                color = colorResource(id = R.color.glitter),
                fontSize = 17.sp,
                fontFamily = FontFamily.SansSerif
            )
        },
        keyboardActions = KeyboardActions(onSearch = { focusManager.clearFocus() }),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            autoCorrect = true,

            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        modifier = Modifier
            .fillMaxWidth().defaultMinSize(minHeight = 36.dp).scale(scaleY = 0.9F, scaleX = 1F),
        textStyle = androidx.compose.ui.text.TextStyle(
            color = colorResource(id = R.color.glitter),
            fontSize = 17.sp,
            fontFamily = FontFamily.SansSerif
        ), shape = RoundedCornerShape(10.dp),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "",
                tint = colorResource(id = R.color.glitter)
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = colorResource(id = R.color.glitter),
            disabledTextColor = Color.Transparent,
            backgroundColor = colorResource(id = R.color.auro_metal_saurus),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SearchHeader(sharedPreference: SharedPreference) {
    Row(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (logo, back, profileImg) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.back_button),
                contentDescription = "",
                contentScale = ContentScale.Inside,
                alignment = Alignment.TopStart,
                modifier = Modifier
                    .constrainAs(logo) {
                        top.linkTo(parent.top, margin = 6.dp)
                        start.linkTo(parent.start, margin = 6.dp)
                    }
            )
//            Text(
//                stringResource(id = R.string.back),
//                fontSize = 17.sp,
//                fontFamily = FontFamily(Font(R.font.poppins_regular)), color = Color.White,
//                modifier = Modifier.constrainAs(back) {
//                    start.linkTo(logo.end, margin = 7.dp)
//                    top.linkTo(parent.top, margin = 16.dp)
//                })

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
        }
    }
}
