package hr.tumiljanovic.mojerezije.ui.state

import hr.tumiljanovic.mojerezije.common.model.ReminderDayOption
import hr.tumiljanovic.mojerezije.common.model.Utility
import java.util.*

data class ReminderSettingsState(
    val reminderTimeOptionSelected: Int = 9,
    val reminderDayOptionSelected: ReminderDayOption = ReminderDayOption.ONE_DAY_EARLIER,
    val reminderDayOptions: List<ReminderDayOption> = listOf(),
    val reminderTimeOptions: List<Int> = listOf(),
    val utilityToReminderStateSortedMap: SortedMap<Utility, Boolean> = sortedMapOf()
)