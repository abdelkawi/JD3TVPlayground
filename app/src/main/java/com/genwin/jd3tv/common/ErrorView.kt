package com.genwin.jd3tv.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.genwin.jd3tv.R

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
        Text(
            text = errorTxt,
            modifier = Modifier.constrainAs(errorMsg) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            color = Color.Black,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )


    }
}
