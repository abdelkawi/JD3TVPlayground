package com.genwin.jd3tv.screens.home.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result.Error
import com.genwin.jd3tv.common.Result.Success
import com.genwin.jd3tv.screens.home.domain.entity.BottomTab
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.CardWithTitle
import com.genwin.jd3tv.screens.splash.presentation.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            when (val res = splashViewModel.getClientData()) {
                is Error -> {

                }
                is Success -> {

                    val tabs = res.data.themeData?.pages?.mapNotNull { it?.ref } ?: emptyList()
                    val homeRes = homeViewModel.getHomeDetails(
                        "home",
                        res.data.id ?: "",
                        res.data.themeData?.id ?: ""
                    )
                    when (homeRes) {
                        is Error -> {
                            Log.d("Error", homeRes.error ?: "wtf")
                        }
                        is Success -> {
                            val sections = homeRes.data
                            sections.forEach { section ->
                                when (val sectionRes = homeViewModel.getSectionItems(
                                    section.endpoint,
                                    section.dataRequest
                                )) {
                                    is Error -> {
                                        section.setItems(emptyList())
                                    }
                                    is Success -> {
                                        section.setItems(sectionRes.data)
                                    }
                                }
                                setContent {
                                    Main(
                                        sections = sections,
                                        tabs.map { BottomTab(it, it) }.subList(0, 4)
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
    fun Main(sections: List<HomeSection>, tabs: List<BottomTab>) {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()
        val navController = rememberNavController()
        Scaffold(
            backgroundColor = Color.Black,
            topBar = { },
            content = {
                NavHost(
                    navController = navController,
                    startDestination = "home",
                ) {
                    tabs.forEach {
                        when (it.route) {
                            "home" -> {
                                composable(it.route) {
                                    Home(sections)
                                }
                            }
                            "profile" -> {
                                composable(it.route) {
                                    Home(sections)
                                }
                            }
                            else -> {
                                composable(it.route) {
                                    Text(
                                        "this is another one ",
                                        fontSize = 30.sp,
                                        color = Color.White
                                    )
                                }
                            }
                        }

                    }
                }
            },
            bottomBar = {
                BottomNavigation {
                    val backStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = backStackEntry?.destination?.route
                    tabs.forEach {
                        BottomNavigationItem(selected = currentRoute == it.route, onClick = {
                            navController.navigate(it.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                            icon = {},
                            label = {
                                Text(text = it.title)
                            }
                        )
                    }
                }
            },
            drawerContent = {
                Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
            },
            drawerBackgroundColor = Color(R.color.indigo)
        )
    }

    @Composable
    fun Home(sections: List<HomeSection>) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
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
                    modifier = Modifier.padding(top = 19.dp, start = 16.dp)
                )
                when (section.type) {
                    Card -> {
                        CardsSection(section = section)
                    }
                    CardWithTitle -> CardsWithTitleSection(section = section)
                    else -> {}
                }
            }
        }
    }

    @Composable
    fun CardsSection(section: HomeSection) {
        LazyRow(modifier = Modifier.padding(start = 16.dp)) {
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

    @Composable
    fun CardsWithTitleSection(section: HomeSection) {
        LazyRow {
            items(section.getItems()) { item ->
                Column {
                    AsyncImage(
                        model = item.mainPhoto?.fileUrl ?: "",
                        placeholder = painterResource(R.drawable.image_1),
                        contentDescription = null,
                        error = painterResource(R.drawable.image_1),
                        modifier = Modifier
                            .height(180.dp)
                            .width(126.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = item.title ?: "",
                        modifier = Modifier.padding(top = 14.dp),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.poppins_regular))
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
        }
    }

    @Composable
    fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
        val items = listOf(
            "Library",
            "Orders",
            "Transactions", "Membership", "Account info", "Payment methods", "Sign out"
        )
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.indigo))
            ) {
                // Header
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(Color.White, shape = CircleShape)
                        .height(34.dp)
                        .width(34.dp)
                ) {
                    Text(
                        text = "AL",
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                        modifier = Modifier
                            .height(32.dp)
                            .width(32.dp)
                            .background(
                                color = colorResource(R.color.languid_lavender),
                                shape = CircleShape
                            )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                )
                Text(
                    text = "Dina Mounib",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                    modifier = Modifier
                        .padding(18.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                )
                Text(
                    text = "dina@rytalo.com",
                    color = Color.White,
                    fontFamily = FontFamily(Font(R.font.poppins_regular)),
                    modifier = Modifier
                        .padding(14.dp)
                        .align(Alignment.CenterHorizontally)
                )
                // List of navigation items
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    DrawerItem(item, selected = currentRoute == item)//,null)
                }
                Spacer(modifier = Modifier.weight(1f))

            }
        }
    }

    @Composable
    fun DrawerItem(item: String, selected: Boolean) {//}, onItemClick: (String) -> Unit) {
        val background = if (selected) R.color.blue_violet else android.R.color.transparent
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
//                .clickable(onClick = { onItemClick(item) })
                .height(45.dp)
                .background(colorResource(id = background))
                .padding(start = 10.dp)
        ) {
            Text(
                text = item,
                fontSize = 18.sp,
                color = Color.White
            )
        }
    }
}

