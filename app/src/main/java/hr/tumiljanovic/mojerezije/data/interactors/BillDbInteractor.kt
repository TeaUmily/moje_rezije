package hr.tumiljanovic.mojerezije.data.interactors

import hr.tumiljanovic.mojerezije.data.database.model.BillDbEntity

interface BillDbInteractor {
    suspend fun getStoredBills() : List<BillDbEntity>
    suspend fun getStoredBillById(id: String) : BillDbEntity
    suspend fun storeBill(billDbEntity: BillDbEntity)
    suspend fun deleteStoredBill(billDbEntity: BillDbEntity)
    suspend fun editStoredBill(billDbEntity: BillDbEntity)
}