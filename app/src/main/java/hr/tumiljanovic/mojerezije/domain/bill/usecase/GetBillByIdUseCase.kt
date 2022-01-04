package hr.tumiljanovic.mojerezije.domain.bill.usecase

import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepository
import javax.inject.Inject

interface GetBillByIdUseCase {
    suspend fun invoke(id: String) : Bill
}

class GetBillByIdUseCaseImpl @Inject constructor(
    private val billRepository: BillRepository
): GetBillByIdUseCase {
    override suspend fun invoke(id: String): Bill = billRepository.fetchById(id)
}