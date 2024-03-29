package com.example.compose.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class BasicAnimation : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Basic()

        }
    }
}

@Preview
@Composable
fun Basic(){

    Box(Modifier.fillMaxSize()){

        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
            BasicAnimationColorChange()
            Spacer(modifier = Modifier.height(20.dp))
            BasicAnimationsTranstions()
        }
    }
}


@Preview
@Composable
fun BasicAnimationColorChange() {

    val toggle = remember {
        mutableStateOf(false)
    }

    val color = animateColorAsState(
        targetValue = if (toggle.value) Color.Yellow else Color.Red,
        animationSpec = spring(stiffness = Spring.StiffnessVeryLow),
        finishedListener = {

        }, label = ""
    )

    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(vertical = 24.dp)
                .background(color = color.value)
        )


        Button(onClick = { toggle.value = !toggle.value }) {
            androidx.compose.material.Text(text = "Animate Me")
        }

    }

}


@Composable
@Preview
fun BasicAnimationsTranstions() {

    val toggle = remember {
        mutableStateOf(false)
    }

    val transtion = updateTransition(targetState = toggle, label = "")

    val size by transtion.animateDp(label = "") {
        if (it.value) 300.dp else 100.dp
    }

    val color by transtion.animateColor(label = "") {
        if (it.value) Color.Yellow else Color.Red
    }


    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(
            modifier = Modifier
                .size(size)
                .padding(vertical = 24.dp)
                .background(color = color)
        )


        Button(onClick = { toggle.value = !toggle.value }) {
            androidx.compose.material.Text(text = "Animate Me")
        }

    }

}

