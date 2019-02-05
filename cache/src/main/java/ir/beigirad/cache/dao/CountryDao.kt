package ir.beigirad.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import ir.beigirad.cache.model.CachedCountry

@Dao
abstract class CountryDao(){
    @Query("SELECT * FROM CachedCountry")
    abstract fun getCountries():Observable<List<CachedCountry>>

    @Query("SELECT Count(*) > 0 FROM CachedCountry")
    abstract fun isCached():Single<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveCountries(countries:List<CachedCountry>)
}