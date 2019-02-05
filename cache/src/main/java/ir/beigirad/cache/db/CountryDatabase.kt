package ir.beigirad.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ir.beigirad.cache.dao.CountryDao
import ir.beigirad.cache.model.CachedCountry

@Database(entities = [CachedCountry::class], version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

}