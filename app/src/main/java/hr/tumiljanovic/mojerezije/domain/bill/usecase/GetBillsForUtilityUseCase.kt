package hr.tumiljanovic.mojerezije.domain.bill.usecase

import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import hr.tumiljanovic.mojerezije.domain.bill.repository.BillRepository
import javax.inject.Inject

interface GetBillsForUtilityUseCase {
    suspend fun invoke(utilityName: String): List<Bill>
}

class GetBillsForUtilityUseCaseImpl @Inject constructor(
    private val billsRepository: BillRepository
) : GetBillsForUtilityUseCase {
    override suspend fun invoke(utilityName: String): List<Bill> =
        billsRepository.fetchAll().filter { it.utilityTitle == utilityName }

}

