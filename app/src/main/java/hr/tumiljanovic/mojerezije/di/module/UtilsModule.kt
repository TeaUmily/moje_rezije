package hr.tumiljanovic.mojerezije.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.tumiljanovic.mojerezije.reminder.ReminderUtils
import hr.tumiljanovic.mojerezije.reminder.ReminderUtilsImpl

@Module
@InstallIn(SingletonComponent::class)
interface UtilsModule {

    @Binds
    fun reminderUtils(reminderUtilsImpl: ReminderUtilsImpl): ReminderUtils
}

