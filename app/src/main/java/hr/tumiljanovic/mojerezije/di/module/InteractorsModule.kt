package hr.tumiljanovic.mojerezije.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.tumiljanovic.mojerezije.data.interactors.BillDbInteractor
import hr.tumiljanovic.mojerezije.data.interactors.impl.BillDbInteractorImpl

@Module
@InstallIn(SingletonComponent::class)
interface InteractorsModule {

    @Binds
    fun billDbInteractor(billDbInteractorImpl: BillDbInteractorImpl): BillDbInteractor
}