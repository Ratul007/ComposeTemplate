package com.example.compose.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.R
import kotlinx.coroutines.delay

class Animation : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AnimationOfficial()

        }
    }
}


@Composable
@Preview(showBackground = true)
fun AnimationOfficial() {

    Box(Modifier.fillMaxSize()) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "Animation", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            AnimationTargetBasedAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "AnimatableAnimation", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))
            AnimatableAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "animate", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            AnimateAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "AnimatedVisibility", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            AnimatedVisibilityAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "CrossFade", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            CrossFadeAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "AnimatedContent", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            AnimatedContentAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "AnimatedContent", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            AnimatedContentSizeAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "RememberInfiniteTransition", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            RememberInfiniteTransitionAnimationAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "UpdateTransition", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            UpdateTransitionAnimation()
            Spacer(modifier = Modifier.height(20.dp))
            androidx.compose.material.Text(text = "AnimatedContent", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            AnimateAsStateAnimation()

        }
    }
}


@Composable
@Preview
fun AnimatedContentSizeAnimation() {

    var isLoading by remember { mutableStateOf(false) }
    val text = if (isLoading) "Short text" else "Very very very long text"

    Button(
        onClick = { isLoading = !isLoading }, modifier = Modifier.animateContentSize(
            animationSpec = tween(
                durationMillis = 300, easing = LinearOutSlowInEasing
            )
        )
    ) {
        androidx.compose.material.Text(
            text = text, textAlign = TextAlign.Center
        )
    }
}

////


@Composable
@Preview
fun CrossFadeAnimation() {

    val switch = remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
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
            .background(color = Color.Green),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.Text(text = "This is Screen A")
    }

}

@Composable
fun ScreenB() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Yellow)
            .height(200.dp),
        contentAlignment = Alignment.Center
    ) {
        androidx.compose.material.Text(text = "This is Screen B")
    }

}


///////


@Composable
@Preview
fun AnimatedVisibilityAnimation() {
    val isVisible = remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AnimatedVisibility(visible = isVisible.value,
            enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + slideIn(initialOffset = {
                IntOffset(
                    x = 100, y = 100
                )
            }),
            exit = fadeOut() + slideOut(targetOffset = { IntOffset(x = -100, y = -100) })

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
            androidx.compose.material.Text(text = "Click")
        }
    }
}

///

@Composable
@Preview
fun AnimatedContentAnimation() {
    Box(Modifier.wrapContentSize()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            var count by remember { mutableIntStateOf(0) }
            Button(onClick = { count++ }) {
                androidx.compose.material.Text("Add")
            }

            Spacer(modifier = Modifier.width(20.dp))

            AnimatedContent(
                targetState = count, transitionSpec = {
                    // Use a fade-in and fade-out animation for a smooth transition.
                    fadeIn() togetherWith fadeOut()
                }, label = ""
            ) { targetCount ->
                // AnimatedContent provides the count value as `targetCount`.
                androidx.compose.material.Text(text = "Count: $targetCount")
            }
        }
    }
}


///////


@Preview
@Composable
fun RememberInfiniteTransitionAnimationAnimation() {

    val infiniteTransition = rememberInfiniteTransition(label = "")
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red, targetValue = Color.Green, animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing), repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Box(
        Modifier
            .height(300.dp)
            .width(300.dp)
            .background(color)
    )

}


///////

private enum class ImageStateUpdateTransition {
    Small, Large
}

@Composable
@Preview
fun UpdateTransitionAnimation() {

    var imageState by remember {
        mutableStateOf(ImageStateUpdateTransition.Small)
    }

    val transition = updateTransition(targetState = imageState, label = "BoxState Transition")

    val borderColor by transition.animateColor(label = "BoxState Color Transition") {
        when (it) {
            ImageStateUpdateTransition.Small -> Color.Green
            ImageStateUpdateTransition.Large -> Color.Magenta
        }
    }

    val size by transition.animateDp(label = "BoxState Size Transition") {
        when (it) {
            ImageStateUpdateTransition.Small -> 90.dp
            ImageStateUpdateTransition.Large -> 130.dp
        }
    }

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            modifier = Modifier
                .size(size = size)
                .clip(shape = CircleShape)
                .border(color = borderColor, shape = CircleShape, width = 3.dp),
            painter = painterResource(id = R.drawable.ic_android_black_24dp),
            contentDescription = "Dog"
        )

        Button(modifier = Modifier.padding(top = 8.dp), onClick = {
            imageState =
                if (imageState == ImageStateUpdateTransition.Small) ImageStateUpdateTransition.Large
                else ImageStateUpdateTransition.Small
        }) {
            androidx.compose.material.Text(text = "Toggle State")
        }
    }
}

///////

@Composable
@Preview
fun AnimateAsStateAnimation() {
    var size by remember {
        mutableFloatStateOf(0.5f)
    }

    val animateScale by animateFloatAsState(
        targetValue = size, animationSpec = tween(durationMillis = 3000), label = ""
    )

    Box(modifier = Modifier
        .scale(scale = animateScale)
        .size(size = 56.dp)
        .background(color = Color.Magenta)
        .clickable {
            size = if (size == 2f) 0.5f else 2f
        }) {}
}


@Composable
@Preview
fun AnimatableAnimation() {
    var ok by remember { mutableStateOf(false) }

    // Start out gray and animate to green/red based on `ok`
    val color = remember { Animatable(Color.Gray) }

    LaunchedEffect(ok) {
        color.animateTo(
            if (ok) Color.Green else Color.Red,
            animationSpec = tween(durationMillis = 10000) // Animation duration
        )
    }

    Box(
        Modifier
            .size(56.dp)
            .background(color.value)
    ) {
        // Simulate a change in `ok` after 3 seconds (for demonstration purposes)
        LaunchedEffect(Unit) {
            delay(3000)
            ok = !ok
        }
    }
}


@Composable
@Preview
fun AnimateAnimation() {

    //InfiniteAnimationDemo Example

    // Create a mutable state for alpha, and update it in the animation.
    val alpha = remember { mutableFloatStateOf(1f) }
    LaunchedEffect(Unit) {
        // Animate from 1f to 0f using an infinitely repeating animation
        animate(
            initialValue = 1f, targetValue = 0f, animationSpec = infiniteRepeatable(
                animation = tween(1000), repeatMode = RepeatMode.Reverse
            )
        ) { value, /* velocity */ _ ->
            // Update alpha mutable state with the current animation value
            alpha.value = value
        }
    }
    Box(Modifier.size(100.dp)) {
        Icon(
            Icons.Filled.Favorite,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = 3.0f, scaleY = 3.0f, alpha = alpha.value
                ),
            tint = Color.Red
        )
    }
}


@Composable
@Preview
private fun AnimationTargetBasedAnimation() {
    // Animation
    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(2000),
            typeConverter = Float.VectorConverter,
            initialValue = 1f, // Start with a scale of 1 (normal size)
            targetValue = 2f    // Scale to 2 (double the size)
        )
    }

    // This will control the animation progress
    val animatedScale = remember { Animatable(initialValue = 1f) }

    // Start the animation when the composable is first recomposed
    LaunchedEffect(anim) {
        val startTime = withFrameNanos { it }

        do {
            val playTime = withFrameNanos { it } - startTime
            val animationValue = anim.getValueFromNanos(playTime)
            animatedScale.animateTo(animationValue)
        } while (!anim.isFinishedFromNanos(playTime))
    }

    // Animated content
    Box(
        modifier = Modifier
            .size(200.dp)
            .graphicsLayer(
                scaleX = animatedScale.value,
                scaleY = animatedScale.value
            ), // Apply animated scale to scaleX and scaleY
        contentAlignment = Alignment.Center,
        content = {
            androidx.compose.material.Text(
                text = "Animated Text",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Black
            )
        }
    )
}

