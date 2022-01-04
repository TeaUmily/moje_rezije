package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun GraphButton(
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClickAction,
        content = {
            Icon(
                painterResource(id = R.drawable.ic_chart),
                contentDescription = "Chart",
                tint = MineShaft
            )
        }
    )
}