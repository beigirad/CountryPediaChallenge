package ir.beigirad.cache

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.beigirad.data.model.CountryEntity
import ir.beigirad.data.repository.CountryCache
import javax.inject.Inject

class CountryCacheImpl @Inject constructor():CountryCache{
    override fun getCountries(): Observable<List<CountryEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCountriesCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveCountries(countries: List<CountryEntity>): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}