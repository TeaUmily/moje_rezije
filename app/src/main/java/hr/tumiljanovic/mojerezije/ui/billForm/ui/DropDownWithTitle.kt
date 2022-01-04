package hr.tumiljanovic.mojerezije.ui.billForm.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.elements.DropDownOptionPicker
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun DropDownWithTitle(
    modifier: Modifier = Modifier,
    onOptionSelected: (String) -> Unit,
    options: List<String>,
    selectedOption: String,
    title: String
) {
    var dropDownExpanded by remember { mutableStateOf(false) }
    val rotationAnimation: Float by animateFloatAsState(
        targetValue = if (dropDownExpanded) 180f else 0f
    )
    val focusManger = LocalFocusManager.current

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = "$title: ",
                style =  MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = selectedOption,
                style =  MaterialTheme.typography.body1,
            )
        }
        IconButton(
            onClick = {
                dropDownExpanded = true
                focusManger.clearFocus()
                      },
            content = {
                Icon(
                    modifier = Modifier.graphicsLayer(rotationZ = rotationAnimation),
                    imageVector = Icons.Default.ArrowDropDown,
                    tint = MineShaft,
                    contentDescription = "Drop down arrow",
                )
                DropDownOptionPicker(
                    options = options,
                    dropDownExpanded = dropDownExpanded,
                    onDismissRequest = { dropDownExpanded = false },
                    onItemClick = {
                        onOptionSelected(it)
                        dropDownExpanded = false
                    }
                )
            }
        )

    }
}