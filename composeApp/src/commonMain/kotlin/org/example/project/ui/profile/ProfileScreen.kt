package org.example.project.ui.profile

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import org.example.project.DeviceType
import org.example.project.model.UiConfig
import org.example.project.ui.component.BodyMediumText
import org.example.project.ui.component.HeadlineLargeText
import org.example.project.ui.component.HeadlineMediumText
import org.example.project.ui.component.TitleMediumText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Suppress("ModifierMissing")
@Composable
fun ProfileScreen() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
    ) {
        val screenWidth = with(LocalDensity.current) { constraints.maxWidth.toDp() }
        val deviceType =
            if (screenWidth < UiConfig.MobileWidth) DeviceType.Mobile else DeviceType.Desktop
        when (deviceType) {
            DeviceType.Mobile -> {
                ProfileMobileContent()
            }

            DeviceType.Desktop -> {
                ProfileDesktopContent()
            }
        }
    }
}

@Composable
fun ProfileMobileContent(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(UiConfig.ContentPadding),
        ) {
            Text(
                text = "Mobile Content",
            )
        }
    }
}

@Composable
fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    HeadlineMediumText(
        modifier = modifier.padding(vertical = UiConfig.SmallPadding),
        text = title,
    )
}

@Composable
fun SectionSubTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    TitleMediumText(
        modifier = modifier.padding(vertical = UiConfig.ExtraSmallPadding),
        text = title,
    )
}

@Composable
fun SectionContent(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.padding(horizontal = UiConfig.MediumPadding),
    ) {
        content()
    }
}

@Composable
fun ProfileHeader(
    profileIcon: DrawableResource,
    name: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(UiConfig.LargeIconSize).clip(CircleShape),
            painter = painterResource(profileIcon),
            contentDescription = "Profile Icon",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.padding(UiConfig.SmallPadding))
        HeadlineLargeText(
            modifier = Modifier.weight(UiConfig.DefaultWeight),
            text = name,
        )
    }
}

@Composable
fun WorksIcon(
    animatedSize: Dp,
    circleColor: Color,
    interactionSource: MutableInteractionSource,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Canvas(
            modifier = Modifier.size(animatedSize),
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = circleColor,
                radius = canvasWidth,
                center = Offset(canvasWidth, canvasHeight),
            )
        }

        Box(
            modifier = Modifier.size(UiConfig.LargeIconSize).padding(
                horizontal = UiConfig.LargePadding,
                vertical = UiConfig.LargePadding,
            ),
            contentAlignment = Alignment.BottomEnd,
        ) {
            BodyMediumText(
                text = "Works",
                modifier = Modifier.hoverable(interactionSource = interactionSource),
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
fun Circle(
    size: Dp,
    color: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color, CircleShape),
    )
}
