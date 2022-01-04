package hr.tumiljanovic.mojerezije.reminder

import android.app.PendingIntent
import android.content.Intent
import java.util.*
import android.content.Context
import android.content.SharedPreferences
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import hr.tumiljanovic.mojerezije.common.constants.IN_APP_DATE_FORMAT
import hr.tumiljanovic.mojerezije.common.constants.REMINDER_DAY_OPTION
import hr.tumiljanovic.mojerezije.common.model.ReminderDayOption
import hr.tumiljanovic.mojerezije.common.model.Utility
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.common.utils.DateUtils
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.usecase.GetBillsUseCase
import hr.tumiljanovic.mojerezije.ui.MainActivity
import javax.inject.Inject


interface ReminderUtils {
    suspend fun buildAndSendReminderForUpcomingBills(context: Context)
}

class ReminderUtilsImpl @Inject constructor(
    private val getBillsUseCase: GetBillsUseCase,
    private val sharedPreferences: SharedPreferences,
    private val resourceRepository: ResourceRepository
): ReminderUtils {

    companion object {
        const val CHANNEL_ID = "112"
        const val CHANNEL_NAME = "AppNotificationChannel"
    }

    override suspend fun buildAndSendReminderForUpcomingBills(context: Context) {
        val upcomingBills = getBillsThatRequireReminder()
        val date = DateUtils.dateToFormat(IN_APP_DATE_FORMAT, getBillsThatRequireReminder()[0].dueDate)
        val title = upcomingBills.joinToString(", ") { it.utilityTitle }
        buildNotification(context, title, date).sendNotification(context)
    }

    private suspend fun getBillsThatRequireReminder() =
        getBillsUseCase.invoke()
            .filter { provideUtilitiesWithReminderOn().contains(it.utilityTitle) }
            .filter {
                val dayOption = sharedPreferences.getInt(REMINDER_DAY_OPTION, ReminderDayOption.ONE_DAY_EARLIER.dayNumber)
                val remainingDaysTillDueDate = (it.dueDate.time - Date().time).toDays()
                remainingDaysTillDueDate == dayOption
             }


    private fun provideUtilitiesWithReminderOn(): MutableList<String> {
        return mutableListOf<String>().apply {
            Utility.values().forEach { utility ->
                if(sharedPreferences.getBoolean(utility.name, false)) {
                    this.add(resourceRepository.getString(utility.titleId))
                }
            }
        }
    }

    private fun buildNotification(context: Context, title: String, date: String): NotificationCompat.Builder {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(hr.tumiljanovic.mojerezije.R.drawable.ic_app_logo)
            .setContentTitle(title)
            .setContentText(context.resources.getString(hr.tumiljanovic.mojerezije.R.string.upcoming_bill) + " " + date)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    private fun NotificationCompat.Builder.sendNotification(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            notify(UUID.randomUUID().variant(), this@sendNotification.build())
        }
    }

    private fun Long.toDays() = (this / 86400000).toInt()
}