package hr.tumiljanovic.mojerezije.domain.bill.usecase

import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepository
import javax.inject.Inject

interface StoreBillUseCase {
    suspend fun invoke(bill: Bill)
}

class StoreBillUseCaseImpl @Inject constructor(
    private val billRepository: BillRepository
): StoreBillUseCase {
    override suspend fun invoke(bill: Bill) {
        return billRepository.store(bill)
    }
}