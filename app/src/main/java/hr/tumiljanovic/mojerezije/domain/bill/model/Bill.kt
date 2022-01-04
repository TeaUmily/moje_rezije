package hr.tumiljanovic.mojerezije.domain.bill.model

import java.util.*

class Bill (
    val id: String,
    val note: String,
    val amount: Float,
    val dueDate: Date,
    val utilityTitle: String,
    val isPaid: Boolean
)