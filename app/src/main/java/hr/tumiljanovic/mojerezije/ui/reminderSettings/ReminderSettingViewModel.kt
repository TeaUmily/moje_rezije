package hr.tumiljanovic.mojerezije.ui.reminderSettings

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hr.tumiljanovic.mojerezije.common.constants.REMINDER_DAY_OPTION
import hr.tumiljanovic.mojerezije.common.constants.REMINDER_TIME_OPTION
import hr.tumiljanovic.mojerezije.common.model.ReminderDayOption
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.ui.state.ReminderSettingsState
import java.util.*
import javax.inject.Inject


@HiltViewModel
class ReminderSettingViewModel @Inject constructor(
    private val resourceRepository: ResourceRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    var reminderSettingsState by mutableStateOf(ReminderSettingsState(
        reminderDayOptions = ReminderDayOption.values().toList(),
        reminderDayOptionSelected = ReminderDayOption.values().find { it.dayNumber ==  sharedPreferences.getInt(REMINDER_DAY_OPTION, ReminderDayOption.ONE_DAY_EARLIER.dayNumber) }
            ?: ReminderDayOption.ONE_DAY_EARLIER,
        reminderTimeOptions = listOf(9, 12, 15, 18),
        utilityToReminderStateSortedMap = provideUtilityToReminderStateSortedMap(),
        reminderTimeOptionSelected = sharedPreferences.getInt(REMINDER_TIME_OPTION, 9)
    ))
        private set


    private fun provideUtilityToReminderStateSortedMap(): SortedMap<Utility, Boolean> {
        val sortedMap = sortedMapOf<Utility, Boolean>()
        Utility.values().forEach { utility ->
            sortedMap[utility] = sharedPreferences.getBoolean(utility.name, false)
        }
        return sortedMap
    }

    fun onReminderDayOptionSelected(dayOptionLabel: String) {
        val dayOption = ReminderDayOption.values().find { resourceRepository.getString(it.labelId) == dayOptionLabel }
        dayOption?.let {
            changeReminderDayOption(dayOption.dayNumber)
            reminderSettingsState = reminderSettingsState.copy(reminderDayOptionSelected = dayOption)
        }
    }

    private fun changeReminderDayOption(dayOption: Int) {
        with(sharedPreferences.edit()) {
            putInt(REMINDER_DAY_OPTION, dayOption)
            apply()
        }
    }

    fun onReminderTimeOptionSelected(timeOption: Int) {
        changeReminderTimeOption(timeOption)
        reminderSettingsState = reminderSettingsState.copy(reminderTimeOptionSelected = timeOption)
    }

    private fun changeReminderTimeOption(timeOption: Int) {
        with(sharedPreferences.edit()) {
            putInt(REMINDER_TIME_OPTION, timeOption)
            apply()
        }
    }

    fun onUtilityReminderStateChanged(utility: Utility, state: Boolean) {
        changeUtilityReminderState(utility, state)
        reminderSettingsState = reminderSettingsState.copy(
            utilityToReminderStateSortedMap = provideUtilityToReminderStateSortedMap()
        )
    }

    private fun changeUtilityReminderState(utility: Utility, state: Boolean) {
        with(sharedPreferences.edit()){
            putBoolean(utility.name, state)
            apply()
        }
    }
}