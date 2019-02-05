package ir.beigirad.remote

import io.reactivex.Observable
import ir.beigirad.data.model.CountryEntity
import ir.beigirad.data.repository.CountryRemote

class CountryRemoteImpl:CountryRemote{
    override fun getCountries(): Observable<List<CountryEntity>> {

    }

}