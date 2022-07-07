package com.genwin.jd3tv.screens.home.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result.Error
import com.genwin.jd3tv.common.Result.Success
import com.genwin.jd3tv.screens.home.data.ItemDetailsRequest
import com.genwin.jd3tv.screens.home.data.ItemDetailsResponse
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.CardWithTitle
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Contest
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val res = homeViewModel.getHomeDetails()
            when (res) {
                is Error -> {
                    Log.d("Error", res.error ?: "wtf")
                }
                is Success -> {
                    val sections = res.data
                    sections.forEach { section ->
                        val res =
                            homeViewModel.getSectionItems(section.endpoint, section.dataRequest)
                        when (res) {
                            is Error -> {
                                section.setItems(emptyList())
                            }
                            is Success -> {
                                section.setItems(res.data)
                            }
                        }
                        setContent {
                            main(sections = sections)
                        }
                    }
                }
            }
        }
    }


    @ExperimentalPagerApi
    @Composable
    fun main(sections: List<HomeSection>) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                home(
                    sections,
                    navController
                )
            }
            composable("profile") {
                profileScreen()
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun home(sections: List<HomeSection>, navController: NavController) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .background(Color.Black)
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            setProfileImage(navController)
            sections.forEach { section ->
                Text(
                    text = section.title,
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(top = 19.dp)
                )
                when (section.type) {
                    Card -> {
                        LazyRow {
                            items(section.getItems()) {
                                AsyncImage(
                                    model = it.mainPhoto?.fileUrl ?: "",
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
                    Contest -> {
                        Surface(
                            color = Color(R.color.dark_jungle_green)
                        ) {
                            HorizontalPager(
                                count = section.getItems().size,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                            ) { page ->
                                // Our page content
                                Column {
                                    AsyncImage(
                                        model = section.getItems()[page].mainPhoto?.fileUrl
                                            ?: "",
                                        placeholder = painterResource(R.drawable.image_1),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(223.dp)
                                            .clip(RoundedCornerShape(10.dp))
                                    )
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Text(
                                        text = section.getItems()[page].title ?: "",
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
            }
        }
    }

    @Composable
    fun profileScreen() {

        Column {
            Text(
                text = "Library",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 19.dp)
            )
            Text(
                text = "Orders",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 19.dp)
            )
            Text(
                text = "Transaction",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 19.dp)
            )
            Text(
                text = "Membership",
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 19.dp)
            )
        }
    }

    @Composable
    private fun setProfileImage(navController: NavController) {
        AsyncImage(
            model = "https://media-public.genwin.app/staging/619eb2cd491b98b6c8c82f3f/61b0ebe29dd7ea5f7af9e44f/images/K-W0rNqrgWgUmXea8OqA_.jpg",
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(620.dp),
            contentScale = ContentScale.FillBounds
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(Color(R.color.white), shape = CircleShape)
        ) {
            Button(onClick = {
                //profileScreen()
                navController.navigate("profile")
            }) {
                Text("AL")
            }
//            Text(
//                text = "AL",
//                textAlign = TextAlign.Center,
//                color = Color.White,
//                modifier = Modifier
//                    .padding(4.dp)
//                    .clickable {
//                        setContent {
//                            profileScreen()
//                        }
//                    }
//            )
        }
    }

}

