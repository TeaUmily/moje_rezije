package hr.tumiljanovic.mojerezije.ui.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable

fun GradientBox(
    modifier: Modifier = Modifier,
    topColor: Color,
    bottomColor: Color,
) {
    Box(
        modifier = modifier
            .height(28.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        topColor,
                        bottomColor
                    )
                )
            )
    )
}