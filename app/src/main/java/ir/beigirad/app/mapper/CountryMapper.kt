package ir.beigirad.app.mapper

import ir.beigirad.app.model.Country
import ir.beigirad.presentation.model.CountryView
import javax.inject.Inject

class CountryMapper @Inject constructor() : ViewMapper<CountryView, Country> {
    override fun mapToView(presentation: CountryView): Country {
        return Country(
                name = presentation.name,
                population = presentation.population,
                flagUrl = presentation.flagUrl
        )
    }

}