package hr.tumiljanovic.mojerezije.domain.bill.usecase

import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepository
import javax.inject.Inject

interface DeleteBillUseCase {
    suspend fun invoke(bill: Bill)
}

class DeleteBillUseCaseImpl @Inject constructor(
    private val billRepository: BillRepository
): DeleteBillUseCase {
    override suspend fun invoke(bill: Bill) = billRepository.delete(bill)
}