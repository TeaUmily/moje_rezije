package hr.tumiljanovic.mojerezije

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import hr.tumiljanovic.mojerezije.common.constants.REMINDER_WORKER_STARTED
import hr.tumiljanovic.mojerezije.reminder.AppWorkerFactory
import hr.tumiljanovic.mojerezije.reminder.ReminderUtilsImpl.Companion.CHANNEL_ID
import hr.tumiljanovic.mojerezije.reminder.ReminderUtilsImpl.Companion.CHANNEL_NAME
import hr.tumiljanovic.mojerezije.reminder.ReminderWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class App: Application(), Configuration.Provider {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var workerFactory: AppWorkerFactory

    companion object {
        private lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        createNotificationChannel()
        startWorker()
    }

    private fun startWorker() {
        if(!sharedPreferences.getBoolean(REMINDER_WORKER_STARTED, false)) {
            val reminderWorkerRequest =
                PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
                    .build()
            WorkManager.getInstance(applicationContext)
                .enqueue(reminderWorkerRequest)
            sharedPreferences.edit().putBoolean(REMINDER_WORKER_STARTED, true).apply()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = CHANNEL_NAME
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

}