package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    actions: @Composable RowScope.() -> Unit = {},
    onBackPressed: (() -> Unit)? = null
) {
    val backButtonAction: (@Composable () -> Unit)? = if (onBackPressed != null) {
        @Composable { BackButton(onBackAction = { onBackPressed() }) }
    } else {
        null
    }

    TopAppBar(
        modifier = modifier,
        elevation = 0.dp,
        actions = actions,
        title = { Text(title, style = MaterialTheme.typography.h6) },
        backgroundColor = Color.White,
        contentColor = MineShaft,
        navigationIcon = backButtonAction
    )
}