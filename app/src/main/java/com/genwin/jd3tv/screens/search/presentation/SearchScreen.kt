package com.genwin.jd3tv.screens.search.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.presentation.Header
import com.genwin.jd3tv.screens.hosts.presentation.HostCell

//
// Created by Dina Mounib on 7/21/22.
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Search(sharedPreference: SharedPreference,navController: NavController,scaffoldState: ScaffoldState) {
   Column(modifier = Modifier
       .fillMaxSize().background(color = Color.Black)) {
       LazyVerticalGrid(
           modifier = Modifier
               .fillMaxSize()
               .padding(start = 16.dp, end = 16.dp)
               .background(color = Color.Black),
           columns = GridCells.Fixed(2),
           verticalArrangement = Arrangement.spacedBy(13.dp),
           horizontalArrangement = Arrangement.spacedBy(16.dp),

           ) {
           item(span = {
               GridItemSpan(2)
           }) {
               Column(
                   modifier = Modifier
                       .padding(top = 6.dp)
                       .fillMaxWidth()
               ) {
                   Header(
                       sharedPreference = sharedPreference,
                       onClick = { navController.popBackStack() },
                       showBack = true,
                       scaffoldState = scaffoldState
                   )
               }
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
}

@Composable
fun searchBar() {
    val text = remember { mutableStateOf("") }
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
            .fillMaxWidth()
            .defaultMinSize(minHeight = 36.dp)
            .scale(scaleY = 0.9F, scaleX = 1F),
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
