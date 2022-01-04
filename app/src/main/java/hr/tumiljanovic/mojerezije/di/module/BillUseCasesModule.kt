package hr.tumiljanovic.mojerezije.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hr.tumiljanovic.mojerezije.domain.bill.usecase.*

@Module
@InstallIn(SingletonComponent::class)
interface BillUseCasesModule {

    @Binds
    fun getBillByIdUseCase(getBillByIdUseCaseImpl: GetBillByIdUseCaseImpl): GetBillByIdUseCase

    @Binds
    fun getBillsUseCase(getBillsUseCaseImpl: GetBillsUseCaseImpl): GetBillsUseCase

    @Binds
    fun storeBillUseCase(storeBillUseCaseImpl: StoreBillUseCaseImpl): StoreBillUseCase

    @Binds
    fun getBillsForUtilityUseCase(getBillsForUtilityUseCaseImpl: GetBillsForUtilityUseCaseImpl): GetBillsForUtilityUseCase

    @Binds
    fun editBillUseCase(editBillUseCaseImpl: EditBillUseCaseImpl): EditBillUseCase

    @Binds
    fun deleteBillUseCase(deleteBillUseCaseImpl: DeleteBillUseCaseImpl): DeleteBillUseCase
}