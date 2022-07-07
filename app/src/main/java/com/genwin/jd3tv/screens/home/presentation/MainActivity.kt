package com.genwin.jd3tv.screens.home.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
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
                when (val sectionRes = homeViewModel.getSectionItems(section.endpoint, section.dataRequest)) {
                  is Error -> {
                    section.setItems(emptyList())
                  }
                  is Success -> {
                    section.setItems(sectionRes.data)
                  }
                }
                setContent {
                  Main(sections = sections, tabs.map { BottomTab(it, it) }.subList(0, 4))
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
              else->{
                composable(it.route){
                Text("this is another one ", fontSize = 30.sp, color = Color.White)
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
      }
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
        Text(text = section.title, color = Color.White, fontFamily = FontFamily(Font(R.font.poppins_semibold)), fontSize = 20.sp, modifier = Modifier.padding(top = 19.dp, start = 16.dp))
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
          Text(text = item.title ?: "", modifier = Modifier.padding(top = 14.dp), color = Color.White, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))
        }
        Spacer(modifier = Modifier.width(10.dp))
      }
    }
  }
}

