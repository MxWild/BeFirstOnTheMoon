package com.spacesale.befirstonthemoon.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.spacesale.befirstonthemoon.database.dao.PlanetDao
import com.spacesale.befirstonthemoon.database.dao.SectorDao
import com.spacesale.befirstonthemoon.database.dao.UserDao
import com.spacesale.befirstonthemoon.database.entity.PlanetEntity
import com.spacesale.befirstonthemoon.database.entity.PurchaseEntity
import com.spacesale.befirstonthemoon.database.entity.SectorEntity
import com.spacesale.befirstonthemoon.database.entity.UserEntity

@Database(
    entities = [
        PlanetEntity::class,
        PurchaseEntity::class,
        SectorEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun planetDao(): PlanetDao
    abstract fun sectorDao(): SectorDao
    abstract fun userDao(): UserDao

    companion object {
        private const val DB_NAME = "beFirstOnTheMoon"

        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }

}