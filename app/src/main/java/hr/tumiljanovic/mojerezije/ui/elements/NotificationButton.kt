package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import hr.tumiljanovic.mojerezije.ui.state.NotificationIconState
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun NotificationButton(
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit,
    notificationIconState: NotificationIconState
) {
    IconButton(
        modifier = modifier,
        onClick = onClickAction,
        content = {
            Icon(
                painterResource(notificationIconState.iconId),
                contentDescription = notificationIconState.name,
                tint = MineShaft
            )
        }
    )
}