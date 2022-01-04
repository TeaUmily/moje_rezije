package hr.tumiljanovic.mojerezije.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hr.tumiljanovic.mojerezije.common.repository.ResourceRepository
import hr.tumiljanovic.mojerezije.common.repository.impl.ResourceRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppContextModule {

    @Provides
    @Singleton
    fun resourceRepo(@ApplicationContext context: Context): ResourceRepository =
        ResourceRepositoryImpl(context.resources)
}
