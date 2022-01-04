package hr.tumiljanovic.mojerezije.common.model

import hr.tumiljanovic.mojerezije.R

enum class ReminderDayOption(val dayNumber: Int, val labelId: Int) {
    ONE_DAY_EARLIER (1, R.string.one_day_earlier),
    TWO_DAYS_EARLIER(2, R.string.two_days_earlier),
    FIVE_DAYS_EARLIER(5, R.string.five_days_earlier),
    SEVEN_DAYS_EARLIER(7, R.string.week_earlier)
}
