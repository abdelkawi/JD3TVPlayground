package com.genwin.jd3tv.screens.home.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.genwin.jd3tv.R
import com.genwin.jd3tv.screens.home.data.DataItem
import com.genwin.jd3tv.screens.home.domain.entity.HomeSection
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Card
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.CardWithTitle
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.Contest
import com.genwin.jd3tv.screens.home.domain.entity.SectionType.ViewPager
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContent {
      main()
    }
  }
  @Preview
  @Composable
  fun main(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
      composable("profile") { profile() }
      composable("home") {
        home(listOf(
          HomeSection("section 1",Card, items = listOf(DataItem("section 1"),DataItem("section 1"))),
            HomeSection("section 2",ViewPager, items = listOf(DataItem("section 1"),DataItem("section 1"))),
            HomeSection("section 3",Contest, items = listOf(DataItem("section 1"),DataItem("section 1"))),
            HomeSection("section 4",CardWithTitle, items = listOf(DataItem("section 1"),DataItem("section 1")))
          ),
          navController) }
    }
  }
  @Composable
  fun home(sections:List<HomeSection>,navController: NavController){
    Column {
      LazyColumn {
        items(sections) { section ->
          sectionRow(section)
        }
      }
      Button(onClick = { navController.navigate("profile")}) {
        Text(text = "Go to profile")
      }
    }

  }
  @Composable
  fun sectionRow(section: HomeSection){
    Column {
      Text(section.title , modifier = Modifier.padding(all = 8.dp))
      LazyRow {
        items(section.items){ item->
          when(section.type){
            Card -> SectionCard(imageRes = R.drawable.image_1)
            ViewPager -> SectionCard(imageRes = R.drawable.image_2)
            CardWithTitle -> Text(item.ref?:"No title", modifier = Modifier.padding(all = 8.dp))
            Contest -> SectionCard(imageRes = R.drawable.image_1)
          }

        }
      }
    }
  }
  @Composable
  fun SectionCard(imageRes:Int){
    Image(painter = painterResource(id = imageRes), contentDescription = "")
  }
  @Composable
  fun profile()
  {
    Text(text = "this is profile page")
  }  
}
