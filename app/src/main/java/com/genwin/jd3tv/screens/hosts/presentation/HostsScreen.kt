package com.genwin.jd3tv.screens.hosts.presentation

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference
import com.genwin.jd3tv.screens.home.presentation.Header

//
// Created by Dina Mounib on 7/20/22.
//
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Host(hostSize: Int, titleStr: String, sharedPreference: SharedPreference) {
    val numbers = (0..hostSize).toList()

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),

        ) {
        item(span = {
            GridItemSpan(2)
        }) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp, start = 16.dp, end = 16.dp)
            ) {
                Header(sharedPreference = sharedPreference, onClick = {})
            }
        }
        item(span = {
            GridItemSpan(2)
        }) {
            Text(
                text = titleStr, fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.poppins_semibold)
                ),
                color = Color.White,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
            )
        }
        item(span ={ GridItemSpan(2)})
        {
            HostRow("", "Host 1","", "Host 2")
        }
        item(span ={ GridItemSpan(2)})
        {
            HostRow("", "Host 1","", "Host 2")
        }
        item(span ={ GridItemSpan(2)})
        {
            HostRow("", "Host 1","", "Host 2")
        }
//        items(numbers.size) {
//            Row(modifier = Modifier.padding(top = 14.dp, start = 5.dp, end = 5.dp)) {
//                //HostCell("", "Host 1")
//
//            }
//        }

    }
}

@Composable
fun HostRow(image1: String, hostName1: String,image2: String, hostName2: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp)
    ) {
        Box(modifier = Modifier.weight(0.5f)) {
            HostCell(image1,hostName1)
        }
        Box(modifier = Modifier.weight(0.5f)) {
            HostCell(image2,hostName2)
        }
    }
}
@Composable
fun HostCell(image: String, hostName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 5.dp, end = 5.dp)
    ) {//horizontalAlignment = Layout.Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(R.drawable.ic_host), contentDescription = "",
            contentScale = ContentScale.Inside,
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = hostName,
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))

        )

    }

}