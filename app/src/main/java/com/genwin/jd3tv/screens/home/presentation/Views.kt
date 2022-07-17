package com.genwin.jd3tv.screens.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.R.drawable
import com.genwin.jd3tv.R.font
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
fun Main(sections: List<HomeSection>, tabs: List<BottomTab>) {
  val navController = rememberNavController()
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
              Home(sections)
            }
          }
          else -> {
            composable(it.route) {
              Text("this is another one ", fontSize = 30.sp, color = Color.White)
            }
          }
        }

      }
    }
    AsyncImage(
      model = "",
      modifier = Modifier
        .constrainAs(profileImg) {
          top.linkTo(parent.top, margin = 16.dp)
          end.linkTo(parent.end, margin = 16.dp)
        }
        .clickable {
        }
        .width(28.dp)
        .height(28.dp)
        .clip(CircleShape)
        .background(Color.White),
      contentDescription = "",
    )
    BottomNavigation(backgroundColor = Color(0xff1f212a), modifier = Modifier
      .constrainAs(bottomBar) {
        bottom.linkTo(parent.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
      }
      .wrapContentHeight()) {
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
            Text(text = it.title, color = Color.White)
          }
        )
      }
    }

  }
}

@Composable
fun Home(sections: List<HomeSection>) {
  Column(
    modifier = Modifier
      .verticalScroll(rememberScrollState())
      .background(Color.Black)
      .wrapContentHeight()
  ) {
    sections.forEach { section ->
      Text(text = section.title, color = Color.White, fontFamily = FontFamily(Font(font.poppins_semibold)), fontSize = 20.sp, modifier = Modifier.padding(top = 19.dp, start = 16.dp))
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
      DotsIndicator(totalDots = section.getItems().size, selectedIndex = state.currentPage, modifier = Modifier.constrainAs(dots) {
        top.linkTo(topSpacer.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
      })
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
  modifier: Modifier
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
            .background(Color(0xFFe225ff))
        )
      } else {
        Box(
          modifier = Modifier
            .size(10.dp)
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
fun Profile() {
  val noImage = true
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
          text = "AL",
          fontSize = 12.sp,
          textAlign = TextAlign.Center,
          fontFamily = FontFamily(Font(R.font.poppins_semibold))
        )
      }
    } else
      AsyncImage(
        model = "", contentDescription = null, modifier = Modifier
          .clip(CircleShape)
          .width(28.dp)
          .height(28.dp)
          .clip(CircleShape)
          .background(Color.White)
      )
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "aliaa abdelkawi", fontFamily = FontFamily(Font(R.font.poppins_semibold)), fontSize = 18.sp, color = Color.White)
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = "mohammedabdelkawi06@gmail.com", fontFamily = FontFamily(Font(R.font.poppins_regular)), fontSize = 14.sp, color = Color.White)
    Spacer(modifier = Modifier.height(44.dp))
    Text(text = "Library", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 16.sp, color = Color(0xFFFFF8F8))
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "Orders", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 16.sp, color = Color(0xFFFFF8F8))
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "Transactions", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 16.sp, color = Color(0xFFFFF8F8))
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "Membership", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 16.sp, color = Color(0xFFFFF8F8))
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "Account info", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 16.sp, color = Color(0xFFFFF8F8))
    Spacer(modifier = Modifier.height(20.dp))
    Text(text = "Payment methods", fontFamily = FontFamily(Font(R.font.poppins_medium)), fontSize = 16.sp, color = Color(0xFFFFF8F8))
    Spacer(modifier = Modifier.height(20.dp))

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
    Text(text = errorTxt, modifier = Modifier.constrainAs(errorMsg) {
      top.linkTo(parent.top)
      bottom.linkTo(parent.bottom)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
    }, color = Color.Black, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.poppins_regular)))


  }
}

@Composable
fun Banner() {
  ConstraintLayout() {
    val (image, dataContainer, gradient) = createRefs()
    AsyncImage(modifier = Modifier.constrainAs(image) {
      top.linkTo(parent.top)
      start.linkTo(parent.start)
      end.linkTo(parent.end)
    }, model = painterResource(id = R.drawable.movie_poster_), contentDescription = null)
    Image(painter = painterResource(id = R.drawable.ic_gradient_transparent), contentDescription = "", modifier = Modifier.constrainAs(gradient) {
      bottom.linkTo(image.bottom)
      start.linkTo(image.start)
      end.linkTo(image.end)
    })
    Column(modifier = Modifier.constrainAs(dataContainer) {
      bottom.linkTo(image.bottom)
      start.linkTo(image.start)
      end.linkTo(image.end)
    }) {
      Text(text = "", fontSize = 50.sp, color = Color.White, fontFamily = FontFamily(Font(R.font.cooper_std_black)))
      Text(text = "", fontSize = 18.sp, color = Color.White, fontFamily = FontFamily(Font(R.font.poppins_medium)))
      Button(onClick = { }, modifier = Modifier.background(colorResource(id = R.color.blue_violet), shape = RoundedCornerShape(18.dp))) {
        Text(text = "Play", color = Color.White)
      }
    }
  }
}