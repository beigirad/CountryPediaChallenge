package ir.beigirad.data.repository

import io.reactivex.Observable
import ir.beigirad.data.model.*

interface CountryRemote {
    fun getCountries():Observable<List<CountryEntity>>
}