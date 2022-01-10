package hr.tumiljanovic.mojerezije.ui.elements

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import hr.tumiljanovic.mojerezije.ui.theme.BoboGray
import hr.tumiljanovic.mojerezije.ui.theme.LightHouse

@ExperimentalComposeUiApi
@Composable
fun DecimalNumberInputField(
    modifier: Modifier = Modifier,
    value: String,
    onStateChanged: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    TextField(modifier = modifier,
        value = value,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        textStyle = MaterialTheme.typography.body1,
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                focusManager.clearFocus()
            }),
        singleLine = true,
        onValueChange = {
            val input: String = if (it.isEmpty()) {
                it
            } else {
                when (it.toDoubleOrNull()) {
                    null -> value
                    else -> it
                }
            }
            onStateChanged(input)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.background,
            unfocusedIndicatorColor = BoboGray
        ),
        placeholder = { Text("0.0") }
    )

}