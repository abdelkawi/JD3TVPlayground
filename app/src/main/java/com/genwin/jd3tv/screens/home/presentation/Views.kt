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
                            setBottomSearchStates(bottomBarState,searchState,isVisible = true)
                        }
                    }
                    "events" -> {
                        composable(it.route) {
                            Event(sharedPreference = sharedPreference, scaffoldState)
                            setBottomSearchStates(bottomBarState,searchState,isVisible = true)
                        }
                    }
                    "hosts" -> {
                        composable(it.route) {
                            Host(10, "Hosts", sharedPreference = sharedPreference, scaffoldState)
                            setBottomSearchStates(bottomBarState,searchState,isVisible = true)
                        }
                    }
                    "shop" -> {
                        composable(it.route) {
                            Shop(sharedPreference = sharedPreference, scaffoldState)
                            setBottomSearchStates(bottomBarState,searchState,isVisible = true)
                        }
                    }
                    "specials" -> {
                        composable(it.route) {
                            SpecialsScreen(
                                sharedPreference = sharedPreference, scaffoldState
                            )
                            setBottomSearchStates(bottomBarState,searchState,isVisible = true)
                        }
                    }
                    else -> {
                        composable(it.route) {
                            Text("this is another one ", fontSize = 30.sp, color = Color.White)
                            setBottomSearchStates(bottomBarState,searchState,isVisible = true)
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
                setBottomSearchStates(bottomBarState,searchState,isVisible = false)
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

fun setBottomSearchStates(bottomBar:MutableState<Boolean>,searchState:MutableState<Boolean>,isVisible:Boolean){
    bottomBar.value = isVisible
    searchState.value = isVisible
}
