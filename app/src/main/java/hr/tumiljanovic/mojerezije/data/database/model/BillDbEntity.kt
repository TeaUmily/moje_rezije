package hr.tumiljanovic.mojerezije.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Bill")
data class BillDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "note")
    val note: String,
    @ColumnInfo(name = "amount")
    val amount: Float,
    @ColumnInfo(name = "due_date")
    val dueDate: String,
    @ColumnInfo(name = "utility_title")
    val utilityTitle: String,
    @ColumnInfo(name = "is_paid")
    val isPaid: Boolean
)