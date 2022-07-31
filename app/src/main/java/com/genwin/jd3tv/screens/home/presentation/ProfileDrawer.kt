package com.genwin.jd3tv.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.genwin.jd3tv.R
import com.genwin.jd3tv.common.SharedPreference


@Composable
fun Profile(
    email: String,
    nickName: String,
    photo: String,
    fullName: String,
    sharedPreference: SharedPreference
) {
    var noImage = true
    if (photo.isNotEmpty())
        noImage = false
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 36.dp, top = 55.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start
        ) {
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
                        text = nickName,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily(Font(R.font.poppins_semibold))
                    )
                }
            } else
                AsyncImage(
                    model = photo, contentDescription = null, modifier = Modifier
                        .clip(CircleShape)
                        .width(28.dp)
                        .height(28.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = fullName,
                fontFamily = FontFamily(Font(R.font.poppins_semibold)),
                fontSize = 18.sp,
                color = Color.White
            )
            Text(
                text = email,
                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                fontSize = 14.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(45.dp))
            Text(
                text = stringResource(R.string.library),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = colorResource(R.color.snow)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = stringResource(id = R.string.orders),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = colorResource(R.color.snow)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = stringResource(id = R.string.transaction),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = colorResource(R.color.snow)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = stringResource(id = R.string.membership),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = colorResource(R.color.snow)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = stringResource(id = R.string.account_info),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = colorResource(R.color.snow)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = stringResource(id = R.string.payment_method),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = colorResource(R.color.snow)
            )
            Spacer(modifier = Modifier.height(35.dp))
            Text(
                text = stringResource(id = R.string.sign_out),
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 16.sp,
                color = colorResource(R.color.snow),
                modifier = Modifier.clickable { sharedPreference.signOut() })
            Spacer(modifier = Modifier.height(35.dp))
        }
    }
}
