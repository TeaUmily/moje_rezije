package hr.tumiljanovic.mojerezije.ui.billForm.ui

import android.widget.CalendarView
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.theme.MineShaft

@Composable
fun FormDueDateSection(
    modifier: Modifier = Modifier,
    selectedDate: String,
    onDatePicked: (String) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
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
                text = stringResource(id = R.string.deadline_for_payment) + ":",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = selectedDate,
                style = MaterialTheme.typography.body1
            )
        }
        IconButton(
            onClick = {
                showDatePicker = !showDatePicker
                focusManger.clearFocus()
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_calendar),
                tint = MineShaft,
                contentDescription = "Calendar"
            )
        }
    }

    if (showDatePicker) {
        Spacer(modifier = Modifier.height(8.dp))
        Surface(elevation = 8.dp) {
            AndroidView(
                { CalendarView(it) },
                modifier = Modifier.wrapContentWidth(),
                update = { views ->
                    views.setOnDateChangeListener { _, year, month, day ->
                        onDatePicked("$day.${month+1}.$year.")
                        showDatePicker = false
                    }
                })
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
