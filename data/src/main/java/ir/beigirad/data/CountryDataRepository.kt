package ir.beigirad.data

import io.reactivex.Observable
import ir.beigirad.data.mapper.CountryMapper
import ir.beigirad.data.repository.CountryCache
import ir.beigirad.data.store.CountryDataStoreFactory
import ir.beigirad.domain.model.Country
import ir.beigirad.domain.repository.CountryRepository
import javax.inject.Inject

class CountryDataRepository @Inject constructor(
    private val cache: CountryCache,
    private val dataFactory: CountryDataStoreFactory,
    private val countryMapper: CountryMapper
) : CountryRepository {
    override fun getCountries(): Observable<List<Country>> {
        return cache.isCountriesCached()
            .flatMapObservable {
                when (it) {
                    true ->
                        dataFactory.getCacheStore().getCountries()

                    false ->
                        dataFactory.getRemoteStore().getCountries()
                            .doAfterNext {
                                dataFactory.getCacheStore().saveCountries(it).subscribe()
                            }
                }
            }
            .map { it.map { countryMapper.mapFromEntity(it) } }
    }
}