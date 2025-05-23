package com.taghavi.slidetounlockjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
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
    val density = LocalDensity.current
    val sliderButtonWidthPx = with(density) { sliderButtonWidthDP.toPx() }
    var sliderPositionPx by remember { mutableFloatStateOf(0f) }
    var boxWidthPx by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .onSizeChanged { size ->
                boxWidthPx = size.width
            }
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    color = outerBtnBackgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Text(
                text = btnText,
                style = btnTextStyle,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(1.dp)
                .offset(x = with(density) { sliderPositionPx.toDp() })
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val newPosition = sliderPositionPx + delta
                        val maxPosition = boxWidthPx - sliderButtonWidthPx
                        sliderPositionPx = newPosition.coerceIn(0f, maxPosition)
                    },
                    onDragStarted = {},
                    onDragStopped = {},
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
            ) {
                SliderButton(
                    sliderBtnWidth = sliderButtonWidthDP,
                    sliderBtnBackgroundColor = sliderBtnBackgroundColor,
                    sliderBtnIcon = sliderBtnIcon,
                )
            }
        }
    }
}

@Composable
fun SliderButton(
    sliderBtnWidth: Dp,
    sliderBtnBackgroundColor: Color,
    @DrawableRes sliderBtnIcon: Int,
) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .width(70.dp)
            .height(55.dp)
            .background(
                color = sliderBtnBackgroundColor,
                shape = RoundedCornerShape(12.dp),
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(sliderBtnIcon),
                contentDescription = "Car Icon",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}