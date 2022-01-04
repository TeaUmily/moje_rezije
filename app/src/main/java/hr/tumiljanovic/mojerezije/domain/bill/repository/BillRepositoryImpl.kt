package hr.tumiljanovic.mojerezije.domain.bill.repository

import hr.tumiljanovic.mojerezije.data.database.model.BillDbMapper
import hr.tumiljanovic.mojerezije.data.interactors.BillDbInteractor
import hr.tumiljanovic.mojerezije.domain.bill.model.Bill
import javax.inject.Inject

class BillRepositoryImpl @Inject constructor(
    private val storedBillInteractor: BillDbInteractor
): BillRepository {
    private val billDbMapper: BillDbMapper = BillDbMapper()

    override suspend fun store(bill: Bill) {
        return storedBillInteractor.storeBill(billDbMapper.mapToEntity(bill))
    }

    override suspend fun fetchById(id: String): Bill {
        return billDbMapper.mapFromEntity(storedBillInteractor.getStoredBillById(id))
    }

    override suspend fun fetchAll(): List<Bill> {
        return storedBillInteractor.getStoredBills()
            .map { billDbMapper.mapFromEntity(it) }
    }

    override suspend fun edit(bill: Bill) {
        return storedBillInteractor.editStoredBill(billDbMapper.mapToEntity(bill))
    }

    override suspend fun delete(bill: Bill) {
        return storedBillInteractor.deleteStoredBill(billDbMapper.mapToEntity(bill))
    }

}