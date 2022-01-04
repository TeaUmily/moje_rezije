package hr.tumiljanovic.mojerezije.ui.state

import hr.tumiljanovic.mojerezije.R

enum class NotificationIconState (val iconId: Int) {
    NOTIFICATION_NORMAL(R.drawable.ic_notification),
    NOTIFICATION_ON(R.drawable.ic_notification_on),
    NOTIFICATION_OFF(R.drawable.ic_notification_off)
}