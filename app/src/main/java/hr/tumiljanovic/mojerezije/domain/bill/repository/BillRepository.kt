package hr.tumiljanovic.mojerezije.domain.bill.repository

import hr.tumiljanovic.mojerezije.domain.bill.model.Bill

interface BillRepository {
    suspend fun store(bill: Bill)
    suspend fun fetchById(id: String): Bill
    suspend fun fetchAll(): List<Bill>
    suspend fun edit(bill: Bill)
    suspend fun delete(bill: Bill)
}