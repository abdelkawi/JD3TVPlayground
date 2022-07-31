package com.genwin.jd3tv.screens.home.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result.Error
import com.genwin.jd3tv.common.Result.Success
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.data.getHomeData
import com.genwin.jd3tv.screens.home.domain.entity.BottomTab
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.splash.data.SplashResponse
import com.genwin.jd3tv.screens.splash.presentation.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  private val homeViewModel: HomeViewModel by viewModels()
  private val splashViewModel: SplashViewModel by viewModels()

  @Inject
  lateinit var sharedPreference: SharedPreference

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
      ) {
        CircularProgressIndicator()
      }

    }
    lifecycleScope.launch {
      when (val res = splashViewModel.getClientData()) {
        is Error -> {
          setContent {
            ErrorView(res.error ?: "Error", {})
          }
        }
        is Success -> {
          setUpTabsAndContent(res)
        }
      }
    }
  }

  private suspend fun setUpTabsAndContent(res: Success<SplashResponse>) {
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
          setContentData(sections, tabs)
        }
      }
    }
  }


  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  private fun setContentData(sections: List<HomeSection>, tabs: List<String>) {
    val staticTabs = mutableListOf<BottomTab>()
    staticTabs.add(BottomTab("Home", "home", R.drawable.ic_home_icon))
    staticTabs.add(BottomTab("Hosts", "hosts", R.drawable.ic_bottom_bar))
    staticTabs.add(BottomTab("Events", "events", R.drawable.ic_shop_icon))
    staticTabs.add(BottomTab("Specials", "specials", R.drawable.ic_specials_icon))
    staticTabs.add(BottomTab("Shop", "shop", R.drawable.ic_shop_icon))
    setContent {
      Box(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Black)
      ) {
        val navController = rememberNavController()
        val searchState = rememberSaveable { (mutableStateOf(true)) }
        val scaffoldState = rememberScaffoldState()
        CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Rtl) {
          Scaffold(
            scaffoldState= scaffoldState,
            drawerContent = {
              ProfileDrawer(sharedPreference)
            },
            content = {
              CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr) {
                Main(
                  sections = getHomeData(sections),
                  tabs = staticTabs,
//                                    tabs = tabs.map { BottomTab(it, it) }
//                                        .subList(1, 6),
                  sharedPreference = sharedPreference,
                  navController,searchState,scaffoldState
                )
              }
            },
            drawerBackgroundColor = Color(0xE6460383),
            floatingActionButtonPosition = FabPosition.Center,
            floatingActionButton = {
              setFloatingButton(navController,searchState)
            }
          )
        }
      }
    }
  }

  @Composable
  private fun ProfileDrawer(sharedPreference: SharedPreference) {
    Profile(
      sharedPreference.getEmail(),
      sharedPreference.getNickName(),
      sharedPreference.getPhoto(),
      sharedPreference.getFullName(),
      sharedPreference
    )
  }

  @Composable
  private fun setFloatingButton(navController: NavController, searchState: MutableState<Boolean>) {
    val configuration = LocalConfiguration.current
    CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr) {
      AnimatedVisibility(
        visible= searchState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
       content = {
         ExtendedFloatingActionButton(
           modifier = Modifier.offset(
             x = (configuration.screenWidthDp.div(
               2
             )
               .minus(26)).dp,
             y = -50.dp
           ),
           icon = {
             Image(
               painter = painterResource(id = R.drawable.ic_search_icon),
               contentDescription = "fab",
               contentScale = ContentScale.FillBounds
             )
           },
           text = { Text("") },
           shape = CircleShape,
           backgroundColor = colorResource(id = android.R.color.transparent),
           onClick = {
             navController.navigate("search")
           },
           elevation = FloatingActionButtonDefaults.elevation(0.dp)
         )
       }
      )


    }
  }
}


