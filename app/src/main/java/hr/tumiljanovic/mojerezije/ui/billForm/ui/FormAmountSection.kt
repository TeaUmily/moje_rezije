package hr.tumiljanovic.mojerezije.ui.billForm.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.elements.DecimalNumberInputField

@ExperimentalComposeUiApi
@Composable
fun FormAmountSection(
    modifier: Modifier = Modifier,
    value: String = "",
    onStateChanged: (String) -> Unit,
    focusRequester: FocusRequester
) {
    Column(
        modifier = modifier
    ) {
        val textStyle = MaterialTheme.typography.body1
        Text(
            text = stringResource(id = R.string.amount) + ":",
            style = textStyle,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            DecimalNumberInputField(
                modifier = Modifier
                    .width(180.dp)
                    .focusRequester(focusRequester),
                value = value,
                onStateChanged = onStateChanged,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.kn),
                style = textStyle
            )
        }
    }
}