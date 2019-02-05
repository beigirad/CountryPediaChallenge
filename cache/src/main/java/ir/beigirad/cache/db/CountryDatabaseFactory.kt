package ir.beigirad.cache.db

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class CountryDatabaseFactory @Inject constructor() {

    private var INSTANCE: CountryDatabase? = null
    private val LOCK = Any()

    fun getInstance(context: Context): CountryDatabase {

        if (INSTANCE == null) {
            synchronized(LOCK) {
                if (INSTANCE == null)
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        CountryDatabase::class.java,
                        "Countries.db"
                    )
//                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                return INSTANCE as CountryDatabase
            }
        }


        return INSTANCE as CountryDatabase

    }
}