package hr.tumiljanovic.mojerezije.data.interactors.impl

import hr.tumiljanovic.mojerezije.data.database.dao.BillDao
import hr.tumiljanovic.mojerezije.data.database.model.BillDbEntity
import hr.tumiljanovic.mojerezije.data.interactors.BillDbInteractor
import javax.inject.Inject

class BillDbInteractorImpl @Inject constructor(
    private val billDao: BillDao
) : BillDbInteractor {
    override suspend fun getStoredBills() = billDao.getAll()

    override suspend fun getStoredBillById(id: String) = billDao.getById(id)

    override suspend fun storeBill(billDbEntity: BillDbEntity) = billDao.insert(billDbEntity)

    override suspend fun deleteStoredBill(billDbEntity: BillDbEntity) = billDao.delete(billDbEntity)

    override suspend fun editStoredBill(billDbEntity: BillDbEntity) = billDao.insert(billDbEntity)


}