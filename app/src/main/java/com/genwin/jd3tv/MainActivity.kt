package com.genwin.jd3tv

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
import com.genwin.jd3tv.SectionType.CardWithTitle
import com.genwin.jd3tv.SectionType.Contest
import com.genwin.jd3tv.SectionType.ViewPager
import kotlin.random.Random


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
      composable("home") { home(navController) }
    }
  }
  @Composable
  fun home(navController: NavController){
    val sections = ArrayList<Section>()
    for (i in 0..4) {
      val section = Section("section ${i}", listOf("movie 1", "movie 2", "movie 3", "movie 4"), getRandomType())
      sections.add(section)
    }
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
  fun sectionRow(section: Section){
    Column {
      Text(section.title , modifier = Modifier.padding(all = 8.dp))
      LazyRow {
        items(section.items){ item->
          Text(item, modifier = Modifier.padding(all = 8.dp))
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
fun getRandomType(): SectionType {
  val random = Random.nextInt(1,4)
  return  when(random){
    1-> ViewPager
    2-> CardWithTitle
    3-> Contest
    else -> SectionType.Card
  }
}
data class Section(val title:String,val items:List<String>,val sectionType: SectionType)
enum class SectionType{
Card,ViewPager,CardWithTitle,Contest
}