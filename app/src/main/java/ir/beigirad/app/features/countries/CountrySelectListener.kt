package ir.beigirad.app.features.countries

import ir.beigirad.app.model.Country

interface CountrySelectListener{
    fun onSelectCountry(selected:Country)
}