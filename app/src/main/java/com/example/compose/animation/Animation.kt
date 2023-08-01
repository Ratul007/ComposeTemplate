package com.example.compose.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.compose.R

@Composable
@Preview
fun AnimatedContentSize() {

    var isLoading by remember { mutableStateOf(false) }
    val text = if (isLoading) "Short text" else "Very very very long text"

    Button(
        onClick = { isLoading = !isLoading },
        modifier = Modifier
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
    ) {
        Text(
            text = text, textAlign = TextAlign.Center
        )
    }
}

////


@Composable
@Preview
fun CrossFade() {

    val switch = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Switch(checked = switch.value, onCheckedChange = { switch.value = it })

        Crossfade(targetState = switch, label = "") {
            if (it.value) {
                ScreenA()
            } else {
                ScreenB()
            }
        }

    }

}

@Composable
fun ScreenA() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = Color.Green), contentAlignment = Alignment.Center
    ) {
        Text(text = "This is Screen A")
    }

}

@Composable
fun ScreenB() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .height(200.dp), contentAlignment = Alignment.Center
    ) {
        Text(text = "This is Screen B")
    }

}


///////


@Composable
@Preview
fun AnimatedVisibility() {
    val isVisible = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(
            visible = isVisible.value,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + slideIn(initialOffset = {
                IntOffset(
                    x = 100,
                    y = 100
                )
            }), exit = fadeOut() + slideOut(targetOffset = { IntOffset(x = -100, y = -100) })

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_android_black_24dp),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(100.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = { isVisible.value = !isVisible.value }) {
            Text(text = "Click")
        }
    }
}

///

@Composable
@Preview
fun AnimatedContent() {
    Box(Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            var count by remember { mutableIntStateOf(0) }
            Button(onClick = { count++ }) {
                Text("Add")
            }

            Spacer(modifier = Modifier.width(20.dp))

            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    // Use a fade-in and fade-out animation for a smooth transition.
                    fadeIn() togetherWith fadeOut()
                }, label = ""
            ) { targetCount ->
                // AnimatedContent provides the count value as `targetCount`.
                Text(text = "Count: $targetCount")
            }
        }
    }
}


@Preview
@Composable
fun RememberInfiniteTransitionAnimation(){

    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(Modifier.height(300.dp).width(300.dp).background(color))

}

@Preview
@Composable
fun  AnimateAsState(){
    val animationTargetState = remember { mutableStateOf(0f) }

    val animatedFloatState = animateFloatAsState(
        // Whenever the target value changes, new animation
        // will start to the new target value
        targetValue = animationTargetState.value,
        animationSpec = tween(durationMillis = 3000)
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
            .clickable {
                // Change the target state to start the animation
                animationTargetState.value = 1f
            }
    ) {
        drawCircle(
            color = Color.White,
            radius = 50f,
            // The circle will fade in with the given animation value
            alpha = animatedFloatState.value
        )
    }

}

@Preview
@Composable
fun  UpdateTransitionAnimation(){

    // Need to remember in order to prevent setting
// the same state value to the transition during
// recomposition.
    val animationTargetState = remember {
        mutableStateOf(AnimationState.START)
    }

// Any state change will trigger animations which
// are created with this transition to the new state
    val transition = updateTransition(
        targetState = animationTargetState.value
    )

    val circleAlpha = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) }
    ) {
        if (it == AnimationState.START) 0f else 1f
    }

    val circleRadius = transition.animateFloat(
        transitionSpec = { tween(durationMillis = 3000) }
    ) {
        if (it == AnimationState.START) 10f else 50f
    }

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        drawCircle(
            color = Color.White,
            radius = circleRadius.value,
            alpha = circleAlpha.value
        )

        // Set animation state value to another state to trigger the animation
        animationTargetState.value = AnimationState.END//
    }

}




