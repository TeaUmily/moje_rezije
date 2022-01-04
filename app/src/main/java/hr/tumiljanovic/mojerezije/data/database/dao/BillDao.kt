package hr.tumiljanovic.mojerezije.data.database.dao

import androidx.room.*
import hr.tumiljanovic.mojerezije.data.database.model.BillDbEntity

@Dao
interface BillDao {
    @Query("SELECT * FROM bill")
    suspend fun getAll(): List<BillDbEntity>

    @Query("SELECT * FROM bill WHERE id=:id")
    suspend fun getById(id: String): BillDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(billDb: BillDbEntity)

    @Delete
    suspend fun delete(billDb: BillDbEntity)
}