package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.R.drawable
import com.genwin.jd3tv.R.font
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.domain.entity.BottomTab
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.CardWithTitle
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Contest
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.ViewPager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun Main(
    sections: List<HomeSection>,
    tabs: List<BottomTab>,
    sharedPreference: SharedPreference,
    navController: NavHostController
) {
    // val navController = rememberNavController()
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (profileImg, content, bottomBar) = createRefs()
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .constrainAs(content) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(bottomBar.top)
                    height = Dimension.fillToConstraints
                }
        ) {
            tabs.forEach {
                when (it.route) {
                    "home" -> {
                        composable(it.route) {
                            Home(sections, sharedPreference)
                        }
                    }
                    "events" -> {
                        composable(it.route) {
                            Event()
                        }
                    }
                    "experiences" -> {
                        composable(it.route) {
                            Host(10, "Hosts")
                        }
                    }
                    "merch" -> {
                        composable(it.route) {
                            Shop(sharedPreference = sharedPreference)
                        }
                    }
                    else -> {
                        composable(it.route) {
                            Text("this is another one ", fontSize = 30.sp, color = Color.White)
                        }
                    }
                }

            }
            composable("search") {
                Search()
            }

        }
//        if (sharedPreference.getPhoto().isNotEmpty())
//            AsyncImage(
//                model = sharedPreference.getPhoto(),
//                contentDescription = null,
//                modifier = Modifier
//                    .clip(CircleShape)
//                    .width(28.dp)
//                    .height(28.dp)
//                    .clip(CircleShape)
//                    .background(Color.White)
//                    .constrainAs(profileImg) {
//                        top.linkTo(parent.top, margin = 16.dp)
//                        end.linkTo(parent.end, margin = 16.dp)
//                    }
//            ) else {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .background(colorResource(id = R.color.persian_blue), shape = CircleShape)
//                    .height(28.dp)
//                    .width(28.dp)
//                    .border(1.dp, Color.White, CircleShape)
//                    .constrainAs(profileImg) {
//                        top.linkTo(parent.top, margin = 16.dp)
//                        end.linkTo(parent.end, margin = 16.dp)
//                    }
//            ) {
//                Text(
//                    text = sharedPreference.getNickName(),
//                    fontSize = 14.sp,
//                    textAlign = TextAlign.Center,
//                    color = Color.White,
//                    fontFamily = FontFamily(Font(R.font.poppins_semibold))
//                )
//            }
//        }

        BottomNavigation(backgroundColor = Color(0xff1f212a), modifier = Modifier
            .constrainAs(bottomBar) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            .wrapContentHeight()) {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            tabs.map { BottomTab(it.title, it.route) }.forEach {
                var isSelected = currentRoute == it.route
                BottomNavigationItem(selected = isSelected, onClick = {
                    navController.navigate(it.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                    icon = {
                        Icon(
                            painterResource(id = R.drawable.ic_bottom_bar),
                            contentDescription = ""
                        )
                    },
                    selectedContentColor = colorResource(id = R.color.psychedelic_purple),
                    unselectedContentColor = Color.White,
                    label = {
                        Text(
                            text = it.title
                        )
                    }
                )
            }
        }
    }

}

@Composable
fun Search() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .background(Color.Black)
                .fillMaxSize()
        ) {

        }
    }
}

@Composable
fun Home(sections: List<HomeSection>, sharedPreference: SharedPreference) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.Black)
            .wrapContentHeight()
    ) {
        Banner(sharedPreference)
        sections.forEach { section ->
            Text(
                text = section.title,
                color = Color.White,
                fontFamily = FontFamily(Font(font.poppins_semibold)),
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 19.dp, start = 16.dp)
            )
            when (section.type) {
                Card -> {
                    viewPagerWithDots(section = section)
                }
                CardWithTitle -> viewPagerWithDots(section = section)
                ViewPager -> {

                }
                Contest -> {
                    viewPagerWithDots(section = section)
                }
                else -> {
                    viewPagerWithDots(section = section)
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
        color = Color.Black
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
                        placeholder = painterResource(drawable.image_1),
                        error = painterResource(drawable.image_1),
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
                        fontFamily = FontFamily(Font(font.poppins_regular)),
                        modifier = Modifier.constrainAs(title) {
                            top.linkTo(image.bottom, margin = 16.dp)
                            start.linkTo(image.start)
                        }
                    )
                    Text(
                        text = "Contest",
                        fontSize = 16.sp,
                        color = Color.White,
                        fontFamily = FontFamily(Font(font.poppins_semibold)),
                        modifier = Modifier
                            .constrainAs(type) {
                                bottom.linkTo(image.bottom, margin = 16.dp)
                                end.linkTo(image.end, margin = 16.dp)
                            }
                            .background(Color.Magenta, shape = RoundedCornerShape(5.dp))
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
                }, Color(0xFFe225ff)
            )
            Spacer(modifier = Modifier
                .padding(4.dp)
                .constrainAs(bottomSpacer) { top.linkTo(dots.bottom) })
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

@Composable
fun Profile(
    email: String,
    nickName: String,
    photo: String,
    fullName: String,
    sharedPreference: SharedPreference
) {
    var noImage = true
    if (photo.isNotEmpty())
        noImage = false
    Column(Modifier.padding(16.dp)) {
        if (noImage) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(Color.White, shape = CircleShape)
                    .height(34.dp)
                    .width(34.dp)
                    .border(1.dp, colorResource(id = R.color.languid_lavender), CircleShape)
            ) {
                Text(
                    text = nickName,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold))
                )
            }
        } else
            AsyncImage(
                model = photo, contentDescription = null, modifier = Modifier
                    .clip(CircleShape)
                    .width(28.dp)
                    .height(28.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            )
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = fullName,
            fontFamily = FontFamily(Font(R.font.poppins_semibold)),
            fontSize = 18.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = email,
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 14.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(45.dp))
        Text(
            text = stringResource(R.string.library),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            color = colorResource(R.color.snow)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.orders),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            color = colorResource(R.color.snow)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.transaction),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            color = colorResource(R.color.snow)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.membership),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            color = colorResource(R.color.snow)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.account_info),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            color = colorResource(R.color.snow)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.payment_method),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            color = colorResource(R.color.snow)
        )
        Spacer(modifier = Modifier.height(35.dp))
        Text(
            text = stringResource(id = R.string.sign_out),
            fontFamily = FontFamily(Font(R.font.poppins_medium)),
            fontSize = 16.sp,
            color = colorResource(R.color.snow),
            modifier = Modifier.clickable { sharedPreference.signOut() })
        Spacer(modifier = Modifier.height(35.dp))
    }
}

@Composable
fun ErrorView(errorTxt: String, reloadAction: () -> Unit) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (retryBtn, errorMsg) = createRefs()
        Button(onClick = { reloadAction.invoke() }, modifier = Modifier.constrainAs(retryBtn) {
            top.linkTo(errorMsg.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            Text(text = "Retry", color = Color.White)
        }
        Text(
            text = errorTxt,
            modifier = Modifier.constrainAs(errorMsg) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )


    }
}

@Composable
fun Header(sharedPreference: SharedPreference) {
    Row(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (logo, profileImg) = createRefs()
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
        }
    }
}

@Composable
fun HomeHeader(sharedPreference: SharedPreference) {
    var expanded by remember { mutableStateOf(false) }
    Row() {
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
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                val (text1, text2, arrow1, arrow2) = createRefs()
                Text(
                    text = stringResource(id = R.string.shows),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
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

                Text(
                    text = stringResource(id = R.string.movies),
                    color = Color.White,
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
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


//                IconButton( onClick = {expanded = true}){
//                    Icon(painter = painterResource(R.drawable.ic_arrow_dropdown), contentDescription =""
//                }
//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = {
//                        expanded = false
//                    }
//                ) {
//                    DropdownMenuItem(onClick = { expanded = false }) {
//                        Text("item1")
//                    }
//                    DropdownMenuItem(onClick = { expanded = false }) {
//                        Text("item2")
//                    }
//                }
            }
        }
    }
}

@Composable
fun Banner(sharedPreference: SharedPreference) {
    ConstraintLayout() {
        val (image, dataContainer, gradient) = createRefs()
//        AsyncImage(modifier = Modifier.constrainAs(image) {
//            top.linkTo(parent.top)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }, model = painterResource(id = R.drawable.movie_poster_), contentDescription = null)

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
        HomeHeader(sharedPreference = sharedPreference)

    }
}