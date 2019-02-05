package ir.beigirad.remote

import io.reactivex.Observable
import ir.beigirad.data.model.CountryEntity
import ir.beigirad.data.repository.CountryRemote
import ir.beigirad.remote.mapper.CountryMapper
import ir.beigirad.remote.service.RemoteService
import javax.inject.Inject

class CountryRemoteImpl @Inject constructor(
    private val remoteService: RemoteService,
    private val countryMapper: CountryMapper
) : CountryRemote {
    override fun getCountries(): Observable<List<CountryEntity>> {
        return remoteService.getCountries()
            .map { it.map { countryMapper.mapFromModel(it) } }
    }

}