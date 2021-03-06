package ir.beigirad.data.repository

import io.reactivex.Completable
import io.reactivex.Observable
import ir.beigirad.data.model.CountryEntity

interface CountryDataStore {
    fun getCountries(): Observable<List<CountryEntity>>

    fun saveCountries(countries: List<CountryEntity>): Completable
}