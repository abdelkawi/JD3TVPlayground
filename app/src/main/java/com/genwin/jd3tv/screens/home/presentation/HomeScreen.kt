package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType

@Composable
fun Home(
    sections: List<HomeSection>,
    sharedPreference: SharedPreference,
    scaffoldState: ScaffoldState
) {
    val homeNavController = rememberNavController()
    NavHost(
        navController = homeNavController,
        startDestination = "main"
    ) {
        composable("main") {
            ConstraintLayout(modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()){
                val (header,homeData) = createRefs()

                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .background(Color.Black)
                        .wrapContentHeight().constrainAs(homeData){
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                ) {

                    sections.forEach { section ->

                        when (section.type) {
                            SectionType.Card -> {
                                CardsSection(section = section)
                            }
                            SectionType.CardWithTitle -> CardsWithTitleSection(section = section)
                            SectionType.ViewPager -> {
                                viewPagerWithDots(section = section)
                            }
                            SectionType.Contest -> {
                                Contest(section = section)
                            }
                            SectionType.Host -> {
                                HostSection(section = section)
                            }
                            SectionType.Shop -> {
                                ShopSection(section = section)
                            }
                            SectionType.FaithItem -> {
                                homeFaithViewPagerItem(section.title)
                            }
                            SectionType.FullItem -> {
                                Banner()
                            }
                        }
                    }
                }
                Box(modifier = Modifier.constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
                    HomeHeader(
                        sharedPreference = sharedPreference,
                        homeNavController,
                        scaffoldState
                    )
                }
            }

        }
        composable("shows") {
            ShowsMovies(
                "Shows",
                sharedPreference = sharedPreference,
                navController = homeNavController,
                scaffoldState = scaffoldState
            )
        }
        composable("movies") {
            ShowsMovies(
                "Movies",
                sharedPreference = sharedPreference,
                navController = homeNavController,
                scaffoldState = scaffoldState
            )
        }
        composable(
            "category/{title}/{selected_cat}",
            arguments = listOf(navArgument("title") { type = NavType.StringType },
                navArgument("selected_cat") { type = NavType.StringType })
        ) {

            CategoryScreen(
                it.arguments?.getString("title") ?: "",
                sharedPreference = sharedPreference,
                navController = homeNavController,
                it.arguments?.getString("selected_cat") ?: "",
                scaffoldState = scaffoldState
            )
        }
    }

}

@Composable
fun HomeHeader(
    sharedPreference: SharedPreference,
    homeNavController: NavHostController,
    scaffoldState: ScaffoldState
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (logo, gradient, container) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.top_bar_gradient),
                contentDescription = "",
                modifier = Modifier.constrainAs(gradient) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                })
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .constrainAs(logo) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(parent.start, margin = 10.dp)
                        end.linkTo(parent.end, margin = 10.dp)
                    }) {
                Header(
                    sharedPreference = sharedPreference,
                    onClick = { },
                    scaffoldState = scaffoldState
                )
            }
            ConstraintLayout(modifier = Modifier.constrainAs(container) {
                top.linkTo(logo.bottom, 26.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                val (text1, text2, arrow1, arrow2) = createRefs()
                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.shows)),
                    onClick = {
                        homeNavController.navigate("shows")
                    },
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    modifier = Modifier.constrainAs(text1) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                Image(
                    painter = painterResource(R.drawable.ic_arrow_dropdown),
                    contentDescription = "", alignment = Alignment.Center,
                    modifier = Modifier.constrainAs(arrow1) {
                        top.linkTo(text1.top)
                        bottom.linkTo(text1.bottom)
                        start.linkTo(text1.end, margin = 6.dp)
                    }
                )

                ClickableText(
                    text = AnnotatedString(stringResource(id = R.string.movies)),
                    onClick = {
                        homeNavController.navigate("movies")
                    },
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_medium))
                    ),
                    modifier = Modifier.constrainAs(text2) {
                        top.linkTo(parent.top)
                        start.linkTo(arrow1.end, margin = 30.dp)
                    }
                )
                Image(
                    painter = painterResource(R.drawable.ic_arrow_dropdown),
                    contentDescription = "", alignment = Alignment.Center,
                    modifier = Modifier.constrainAs(arrow2) {
                        top.linkTo(text2.top)
                        bottom.linkTo(text2.bottom)
                        start.linkTo(text2.end, margin = 6.dp)
                    }
                )
            }
        }
    }
}




