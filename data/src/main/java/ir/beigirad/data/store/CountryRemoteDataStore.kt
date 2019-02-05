package ir.beigirad.data.store

import io.reactivex.Completable
import io.reactivex.Observable
import ir.beigirad.data.model.CountryEntity
import ir.beigirad.data.repository.CountryDataStore
import ir.beigirad.data.repository.CountryRemote
import javax.inject.Inject

class CountryRemoteDataStore @Inject constructor(private val remote: CountryRemote) : CountryDataStore {
    override fun getCountries(): Observable<List<CountryEntity>> {
        return remote.getCountries()
    }
}