package ir.beigirad.domain.repository

import io.reactivex.Observable
import ir.beigirad.domain.model.Country

interface CountryRepository{
    fun getCountries():Observable<List<Country>>
}