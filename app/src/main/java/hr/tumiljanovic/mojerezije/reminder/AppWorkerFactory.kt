package hr.tumiljanovic.mojerezije.reminder

import android.content.Context
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import hr.tumiljanovic.mojerezije.domain.bill.usecase.GetBillsUseCase
import javax.inject.Inject


class AppWorkerFactory @Inject constructor(private val reminderUtils: ReminderUtils) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ReminderWorker = ReminderWorker(appContext, workerParameters, reminderUtils)
}