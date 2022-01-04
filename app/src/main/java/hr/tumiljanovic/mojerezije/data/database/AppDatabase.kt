package hr.tumiljanovic.mojerezije.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.tumiljanovic.mojerezije.data.database.dao.BillDao
import hr.tumiljanovic.mojerezije.data.database.model.BillDbEntity

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "AppDatabase"

@Database(
    entities = [BillDbEntity::class],
    version = DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun billDao(): BillDao

    companion object {
        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}