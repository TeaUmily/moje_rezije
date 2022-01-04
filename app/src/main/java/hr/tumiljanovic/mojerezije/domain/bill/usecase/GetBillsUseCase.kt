package hr.tumiljanovic.mojerezije.domain.bill.usecase

import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepository
import javax.inject.Inject

interface GetBillsUseCase {
    suspend fun invoke() : List<Bill>
}

class GetBillsUseCaseImpl @Inject constructor(
    private val billRepository: BillRepository
): GetBillsUseCase {
    override suspend fun invoke(): List<Bill> = billRepository.fetchAll()
}