package hr.tumiljanovic.mojerezije.reminder

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import hr.tumiljanovic.mojerezije.domain.bill.usecase.GetBillsUseCase
import javax.inject.Inject


@HiltWorker
class ReminderWorker @AssistedInject constructor(@Assisted val context: Context,
                                                 @Assisted params: WorkerParameters,
                                                 private val reminderUtils: ReminderUtils
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        reminderUtils.buildAndSendReminderForUpcomingBills(context)
        return Result.success()
    }
}