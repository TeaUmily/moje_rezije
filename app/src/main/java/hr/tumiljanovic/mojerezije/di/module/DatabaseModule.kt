package hr.tumiljanovic.mojerezije.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.tumiljanovic.mojerezije.data.database.AppDatabase
import hr.tumiljanovic.mojerezije.data.database.dao.BillDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.buildDatabase(context)
    }

    @Provides
    fun provideBillDao(appDatabase: AppDatabase): BillDao {
        return appDatabase.billDao()
    }
}