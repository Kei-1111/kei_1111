package org.example.project.ui.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kei_1111.composeapp.generated.resources.Res
import kei_1111.composeapp.generated.resources.img_profile_icon
import kotlinx.coroutines.delay
import org.example.project.model.UiDimensions
import org.example.project.ui.theme.NotoSansJpFamily
import org.jetbrains.compose.resources.painterResource

@Composable
fun SplashScreen(
    toProfile: () -> Unit
) {
    val text = "Hello!!"
    var s by remember { mutableStateOf("") }

    val alphaAnim = remember { Animatable(0f) } // フェードイン用アニメーション
    val offsetXAnim = remember { Animatable(-100f) } // スライドイン用アニメーション



    LaunchedEffect(MaterialTheme.typography.headlineLarge.fontFamily == NotoSansJpFamily()) {

        alphaAnim.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )

        offsetXAnim.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
        )

        text.indices.forEach { index ->
            delay(100)
            s = text.substring(0..index)
        }
        delay(1500)
        toProfile()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.alpha(alphaAnim.value)
        ) {
            Image(
                painter = painterResource(Res.drawable.img_profile_icon),
                contentDescription = "Profile icon",
                modifier = Modifier
                    .offset(x = offsetXAnim.value.dp) // スライドインの位置指定
                    .size(UiDimensions.mediumIconSize)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            if (s != "") {
                Spacer(modifier = Modifier.padding(10.dp))
            }
            Text(
                text = s,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}