package ir.beigirad.presentation.mapper

import ir.beigirad.domain.model.Country
import ir.beigirad.presentation.model.CountryView
import javax.inject.Inject

class CountryViewMapper @Inject constructor() : Mapper<CountryView, Country> {
    override fun mapToView(domain: Country): CountryView {
        return CountryView(
            name = domain.name,
            flagUrl = domain.flagUrl,
            population = domain.population
        )
    }
}