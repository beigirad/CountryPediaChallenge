package ir.beigirad.app.di.module

import dagger.Binds
import dagger.Module
import ir.beigirad.data.CountryDataRepository
import ir.beigirad.domain.repository.CountryRepository

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: CountryDataRepository): CountryRepository

}