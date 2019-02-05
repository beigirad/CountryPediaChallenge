package ir.beigirad.data

import io.reactivex.Observable
import ir.beigirad.data.mapper.CountryMapper
import ir.beigirad.data.repository.CountryRemote
import ir.beigirad.domain.model.Country
import ir.beigirad.domain.repository.CountryRepository
import javax.inject.Inject

class CountryDataRepository @Inject constructor(
    private val remoteDataStore: CountryRemote,
    private val countryMapper: CountryMapper
) : CountryRepository {
    override fun getCountries(): Observable<List<Country>> {
        return remoteDataStore.getCountries()
            .map { it.map { countryMapper.mapFromEntity(it) } }
    }

}