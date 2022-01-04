package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun CloseButton(
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit
) {
    IconButton(
        modifier = modifier.padding(4.dp),
        onClick = onCloseClicked,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                tint = MineShaft,
                contentDescription = "Close"
            )
        }
    )
}