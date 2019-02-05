package ir.beigirad.remote.mapper

import ir.beigirad.data.model.CountryEntity
import ir.beigirad.remote.model.CountryModel
import javax.inject.Inject

class CountryMapper @Inject constructor(): ModelMapper<CountryModel, CountryEntity> {
    override fun mapFromModel(model: CountryModel): CountryEntity {
        return CountryEntity(
            name = model.name ?: "",
            population = model.population ?: 0,
            flagUrl = model.flag ?: ""
        )
    }
}