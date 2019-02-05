package ir.beigirad.remote.service

import io.reactivex.Observable
import ir.beigirad.remote.model.CountryModel
import retrofit2.http.GET

interface RemoteService {

    @GET("rest/v2/all")
    fun getCountries(): Observable<List<CountryModel>>
}