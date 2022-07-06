package com.genwin.jd3tv.screens.home.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ContentScale.Companion
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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


}

