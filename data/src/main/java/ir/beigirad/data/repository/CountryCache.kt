package ir.beigirad.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ir.beigirad.data.model.CountryEntity

interface CountryCache {
    fun getCountries(): Observable<List<CountryEntity>>

    fun isCountriesCached(): Single<Boolean>

    fun saveCountries(countries: List<CountryEntity>): Completable
}