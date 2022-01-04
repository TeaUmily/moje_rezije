package hr.tumiljanovic.mojerezije.domain.bill.usecase

import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepository
import javax.inject.Inject

interface EditBillUseCase {
    suspend fun invoke(bill: Bill)
}

class EditBillUseCaseImpl @Inject constructor(
    private val billRepository: BillRepository
): EditBillUseCase {
    override suspend fun invoke(bill: Bill) = billRepository.edit(bill)
}