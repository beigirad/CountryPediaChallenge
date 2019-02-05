package ir.beigirad.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.beigirad.cache.db.CountryDatabase
import ir.beigirad.cache.mapper.CountryMapper
import ir.beigirad.data.model.CountryEntity
import ir.beigirad.data.repository.CountryCache
import timber.log.Timber
import javax.inject.Inject

class CountryCacheImpl @Inject constructor(
    private val db: CountryDatabase,
    private val countryMapper: CountryMapper
) : CountryCache {
    override fun getCountries(): Observable<List<CountryEntity>> {
        Timber.d("getCountries")
        return db.countryDao().getCountries()
            .map { it.map { countryMapper.mapFromCache(it) } }
    }

    override fun isCountriesCached(): Single<Boolean> {
        Timber.d("isCountriesCached")
        return db.countryDao().isCached()
    }

    override fun saveCountries(countries: List<CountryEntity>): Completable {
        Timber.d("saveCountries ${countries.size}")
        return Completable.fromCallable {
            db.countryDao().saveCountries(
                countries.map {
                    countryMapper.mapToCache(it)
                })
        }
    }
}