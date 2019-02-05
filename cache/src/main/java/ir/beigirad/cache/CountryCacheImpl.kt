package ir.beigirad.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.beigirad.cache.db.CountryDatabase
import ir.beigirad.cache.mapper.CountryMapper
import ir.beigirad.data.model.CountryEntity
import ir.beigirad.data.repository.CountryCache
import javax.inject.Inject

class CountryCacheImpl @Inject constructor(
    private val db: CountryDatabase,
    private val countryMapper: CountryMapper
) : CountryCache {
    override fun getCountries(): Observable<List<CountryEntity>> {
        return db.countryDao().getCountries()
            .map { it.map { countryMapper.mapFromCache(it) } }
    }

    override fun isCountriesCached(): Single<Boolean> {
        return db.countryDao().isCached()
    }

    override fun saveCountries(countries: List<CountryEntity>): Completable {
        return Completable.fromCallable {
            db.countryDao().saveCountries(
                countries.map {
                    countryMapper.mapToCache(it)
                })
        }
    }
}