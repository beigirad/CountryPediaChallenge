package ir.beigirad.app.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ir.beigirad.cache.CountryCacheImpl
import ir.beigirad.cache.db.CountryDatabase
import ir.beigirad.cache.db.CountryDatabaseFactory
import ir.beigirad.data.repository.CountryCache
import javax.inject.Singleton

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Singleton
        @Provides
        @JvmStatic
        fun provideDatabase(application: Application): CountryDatabase {
            return CountryDatabaseFactory().getInstance(application)
        }
    }

    @Binds
    abstract fun bindDatabase(countryCache: CountryCacheImpl): CountryCache


}