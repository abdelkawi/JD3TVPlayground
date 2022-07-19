package com.genwin.jd3tv.screens.home.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.Result.Error
import com.genwin.jd3tv.common.Result.Success
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.domain.entity.BottomTab
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.splash.presentation.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
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
    setContent{
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
                  CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Rtl) {
                    ModalDrawer(
                      drawerContent = {
                        Profile(sharedPreference.getEmail(),sharedPreference.getNickName(),sharedPreference.getPhoto(),sharedPreference.getFullName(),sharedPreference)
                      },
                      content = {
                        CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr) {
                        Main(
                          sections = sections,
                          tabs = tabs.map { BottomTab(it, it) }.subList(0, 4)
                        )
                      }},
                      drawerBackgroundColor = Color(0xE6460383)
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

  @OptIn(ExperimentalPagerApi::class)
  @Composable
  fun Contest(section: HomeSection) {
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


  @Composable
  fun CardsSection(section: HomeSection) {
    Column {
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

