package ir.beigirad.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ir.beigirad.data.model.CountryEntity
import ir.beigirad.data.repository.CountryCache
import ir.beigirad.data.repository.CountryDataStore
import javax.inject.Inject

class CountryCacheDataStore @Inject constructor(private val cache: CountryCache) : CountryDataStore {
    override fun saveCountries(countries: List<CountryEntity>): Completable {
        return cache.saveCountries(countries)
    }

    override fun getCountries(): Observable<List<CountryEntity>> {
        return cache.getCountries()
    }
}