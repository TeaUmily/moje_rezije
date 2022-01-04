package hr.tumiljanovic.mojerezije.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepository
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun billsRepo(billRepositoryImpl: BillRepositoryImpl) : BillRepository
}