package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onBackAction: () -> Unit
) {
    IconButton(
        modifier = modifier.padding(4.dp),
        onClick = onBackAction,
        content = {
            Icon(
                Icons.Default.ArrowBack,
                tint = MineShaft,
                contentDescription = "Back"
            )
        }
    )
}