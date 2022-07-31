package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
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
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.R.drawable.*
import com.genwin.jd3tv.R.font.*
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.events.presentation.Event
import com.genwin.jd3tv.screens.home.domain.entity.BottomTab
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.*
import com.genwin.jd3tv.screens.hosts.presentation.Host
import com.genwin.jd3tv.screens.search.presentation.Search
import com.genwin.jd3tv.screens.shop.presentation.Shop
import com.genwin.jd3tv.screens.specials.presentation.SpecialsScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Composable
fun Main(
    sections: List<HomeSection>,
    tabs: List<BottomTab>,
    sharedPreference: SharedPreference,
    navController: NavHostController,
    searchState: MutableState<Boolean>,
    scaffoldState: ScaffoldState

) {
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (content, bottomBar) = createRefs()
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
                            Home(sections, sharedPreference, scaffoldState)
                            bottomBarState.value = true
                            searchState.value = true
                        }
                    }
                    "events" -> {
                        composable(it.route) {
                            Event(sharedPreference = sharedPreference, scaffoldState)
                            bottomBarState.value = true
                            searchState.value = true
                        }
                    }
                    "hosts" -> {
                        composable(it.route) {
                            Host(10, "Hosts", sharedPreference = sharedPreference, scaffoldState)
                            bottomBarState.value = true
                            searchState.value = true
                        }
                    }
                    "shop" -> {
                        composable(it.route) {
                            Shop(sharedPreference = sharedPreference, scaffoldState)
                            bottomBarState.value = true
                            searchState.value = true
                        }
                    }
                    "specials" -> {
                        composable(it.route) {
                            SpecialsScreen(
                                sharedPreference = sharedPreference, scaffoldState
                            )
                            bottomBarState.value = true
                            searchState.value = true
                        }
                    }
                    else -> {
                        composable(it.route) {
                            Text("this is another one ", fontSize = 30.sp, color = Color.White)
                            bottomBarState.value = true
                            searchState.value = true
                        }
                    }

                }

            }

            composable("search") {
                Search(
                    sharedPreference = sharedPreference,
                    navController = navController,
                    scaffoldState = scaffoldState
                )
                bottomBarState.value = false
                searchState.value = false
            }

        }
        AnimatedVisibility(
            visible = bottomBarState.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            modifier = Modifier
                .constrainAs(bottomBar) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .wrapContentHeight(),
            content = {
                BottomNavigation(
                    backgroundColor = Color(0xff1f212a)
                ) {
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = backStackEntry?.destination?.route
                    tabs.forEach {
                        val isSelected = currentRoute == it.route
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
                                    painterResource(id = it.icon),
                                    contentDescription = ""
                                )
                            },
                            selectedContentColor = colorResource(id = R.color.psychedelic_purple),
                            unselectedContentColor = Color.White,
                            label = {
                                Text(
                                    text = it.title,
                                    fontSize = 12.sp,
                                    fontFamily = FontFamily((Font(poppins_semibold))),
                                    maxLines = 1
                                )
                            }
                        )
                    }
                }
            }
        )

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
            fontFamily = FontFamily(Font(poppins_semibold)),
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
                            placeholder = painterResource(image_1),
                            error = painterResource(image_1),
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
                            placeholder = painterResource(image_1),
                            error = painterResource(image_1),
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
                            fontFamily = FontFamily(Font(poppins_regular)),
                            modifier = Modifier.constrainAs(title) {
                                top.linkTo(image.bottom, margin = 16.dp)
                            }
                        )
                        Text(
                            text = "Contest".uppercase(),
                            fontSize = 13.sp,
                            color = Color.White,
                            fontFamily = FontFamily(Font(poppins_semibold)),
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


@Composable
fun Header(
    sharedPreference: SharedPreference,
    onClick: () -> Unit,
    scaffoldState: ScaffoldState,
    showBack: Boolean = false
) {
    Row(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (logo, profileImg) = createRefs()
            if (showBack)
                Image(
                    painter = painterResource(id = back_button),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.TopStart,
                    modifier = Modifier
                        .constrainAs(logo) {
                            top.linkTo(parent.top, margin = 6.dp)
                            start.linkTo(parent.start, margin = 6.dp)
                        }
                        .clickable { onClick.invoke() }
                )
            else
                Image(
                    painter = painterResource(id = ic_jd_tv_logo),
                    contentDescription = "",
                    contentScale = ContentScale.Inside,
                    alignment = Alignment.TopStart,
                    modifier = Modifier.constrainAs(logo) {
                        top.linkTo(parent.top, margin = 6.dp)
                        start.linkTo(parent.start)
                    }
                )
            val scope = rememberCoroutineScope()
            if (sharedPreference.getPhoto().isNotEmpty())
                AsyncImage(
                    model = sharedPreference.getPhoto(),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable { scope.launch { scaffoldState.drawerState.open() } }
                        .clip(CircleShape)
                        .width(28.dp)
                        .height(28.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .constrainAs(profileImg) {
                            top.linkTo(parent.top, margin = 6.dp)
                            end.linkTo(parent.end)
                        }
                ) else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(colorResource(id = R.color.persian_blue), shape = CircleShape)
                        .height(28.dp)
                        .clickable { scope.launch { scaffoldState.drawerState.open() } }
                        .width(28.dp)
                        .border(1.dp, Color.White, CircleShape)
                        .constrainAs(profileImg) {
                            top.linkTo(parent.top, margin = 6.dp)
                            end.linkTo(parent.end)
                        }
                ) {
                    Text(
                        text = sharedPreference.getNickName(),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = FontFamily(Font(poppins_semibold))
                    )
                }
            }
        }
    }
}


@Composable
fun Banner(
) {
    ConstraintLayout() {
        val (image, dataContainer, gradient) = createRefs()
        Image(
            painter = painterResource(id = movie_poster_),
            contentDescription = "",
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Image(
            painter = painterResource(id = ic_gradient_transparent),
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
                fontFamily = FontFamily(Font(cooper_std_black))
            )
            Text(
                text = "Drama * Romance",
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(Font(poppins_medium))
            )
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(play_button),
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
                        placeholder = painterResource(image_1),
                        contentDescription = null,
                        error = painterResource(image_1),
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
                        fontFamily = FontFamily(Font(poppins_regular))
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
                    placeholder = painterResource(image_1),
                    contentDescription = null,
                    error = painterResource(image_1),
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
                    placeholder = painterResource(image_1),
                    contentDescription = null,
                    error = painterResource(image_1),
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
                    fontFamily = FontFamily(Font(poppins_regular))
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
                placeholder = painterResource(image_1),
                contentDescription = null,
                error = painterResource(image_1),
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
                placeholder = painterResource(ic_product),
                contentDescription = null,
                error = painterResource(ic_product),
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