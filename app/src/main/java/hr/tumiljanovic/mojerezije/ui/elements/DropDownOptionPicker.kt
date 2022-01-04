package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun DropDownOptionPicker(
    modifier: Modifier = Modifier,
    options: List<String> = listOf(),
    dropDownExpanded: Boolean = false,
    onDismissRequest: () -> Unit,
    onItemClick: (String) -> Unit
) {
    DropdownMenu(
        modifier = modifier,
        expanded = dropDownExpanded,
        onDismissRequest = onDismissRequest
    ) {
        for (option in options) {
            DropdownMenuItem(
                onClick = { onItemClick(option) }
            ) {
                Text(option)
            }
        }
    }
}