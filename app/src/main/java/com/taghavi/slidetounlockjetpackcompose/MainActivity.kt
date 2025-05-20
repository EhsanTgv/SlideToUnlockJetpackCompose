package com.taghavi.slidetounlockjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.FlowRowScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taghavi.slidetounlockjetpackcompose.ui.theme.SlideToUnlockJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlideToUnlockJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun SlideToBookButton(
    btnText: String,
    btnTextStyle: TextStyle,
    outerBtnBackgroundColor: Color,
    sliderBtnBackgroundColor: Color,
    @DrawableRes sliderBtnIcon: Int,
    onBtnSwipe: () -> Unit
) {
    val sliderButtonWidthDP = 70.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
    ){
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = outerBtnBackgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
        ){
            Text(
                text = btnText,
                style = btnTextStyle,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
        ){
            SliderButton(
                sliderBtnWidth = sliderButtonWidthDP,
                sliderBtnBackgroundColor = sliderBtnBackgroundColor,
                sliderBtnIcon = sliderBtnIcon,
            )
        }
    }
}