package hr.tumiljanovic.mojerezije.ui.reminderSettings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import hr.tumiljanovic.mojerezije.R
import hr.tumiljanovic.mojerezije.ui.billForm.ui.DropDownWithTitle
import hr.tumiljanovic.mojerezije.ui.elements.TopBar
import hr.tumiljanovic.mojerezije.ui.reminderSettings.ui.UtilityReminderToggle
import hr.tumiljanovic.mojerezije.ui.theme.LightHouse

@Composable
fun ReminderSettingsScreen(
    upPressCallback: () -> Unit,
    viewModel: ReminderSettingViewModel = hiltViewModel(),
) {
    val reminderSettingsState = viewModel.reminderSettingsState
    Scaffold(topBar = {
        TopBar(title = stringResource(id = R.string.reminder),
            onBackPressed = upPressCallback
        )
    },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxHeight()
                    .padding(16.dp, 16.dp))
            {
                DropDownWithTitle(
                    onOptionSelected = viewModel::onReminderDayOptionSelected,
                    options = reminderSettingsState.reminderDayOptions.map { stringResource(id = it.labelId) },
                    selectedOption = stringResource(id = reminderSettingsState.reminderDayOptionSelected.labelId),
                    title = stringResource(id = R.string.send_reminder)
                )
                Spacer(modifier = Modifier.height(18.dp))
                /* Text(
                    text = stringResource(id = R.string.time_to_send_reminder) + ":",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    reminderSettingsState.reminderTimeOptions.forEach {
                        Chip(
                            isSelected = it == reminderSettingsState.reminderTimeOptionSelected,
                            label = "$it:00",
                            onSelected = { viewModel.onReminderTimeOptionSelected(it) }
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))*/

                Divider(color = LightHouse)
                reminderSettingsState.utilityToReminderStateSortedMap.forEach { item ->
                    UtilityReminderToggle(
                        utility = item.key,
                        isReminderOn = item.value,
                        onStateChanged = { state -> viewModel.onUtilityReminderStateChanged(item.key, state) }
                    )
                }
            }
        })
}