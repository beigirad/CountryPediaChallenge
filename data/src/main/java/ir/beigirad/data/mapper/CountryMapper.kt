package ir.beigirad.data.mapper

import ir.beigirad.data.model.CountryEntity
import ir.beigirad.domain.model.Country

class CountryMapper:EntityMapper<CountryEntity,Country>{
    override fun mapFromEntity(entity: CountryEntity): Country {
        return Country(name = entity.name,
            flagUrl = entity.flagUrl,
            population = entity.population)
    }

    override fun mapToEntity(domain: Country): CountryEntity {
        return CountryEntity(name = domain.name,
            flagUrl = domain.flagUrl,
            population = domain.population)    }
}