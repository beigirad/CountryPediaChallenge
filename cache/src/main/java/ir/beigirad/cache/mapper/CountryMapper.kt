package ir.beigirad.cache.mapper

import ir.beigirad.cache.model.CachedCountry
import ir.beigirad.data.model.CountryEntity
import javax.inject.Inject

class CountryMapper @Inject constructor() : CacheMapper<CachedCountry, CountryEntity> {
    override fun mapToCache(entity: CountryEntity): CachedCountry {
        return CachedCountry(
            name = entity.name,
            flagUrl = entity.flagUrl,
            population = entity.population
        )
    }

    override fun mapFromCache(cache: CachedCountry): CountryEntity {
        return CountryEntity(
            name = cache.name,
            population = cache.population,
            flagUrl = cache.flagUrl
        )
    }

}