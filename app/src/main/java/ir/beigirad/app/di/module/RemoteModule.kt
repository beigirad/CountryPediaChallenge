package ir.beigirad.app.di.module

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import ir.beigirad.app.BuildConfig
import ir.beigirad.data.repository.CountryRemote
import ir.beigirad.remote.CountryRemoteImpl
import ir.beigirad.remote.service.RemoteService
import ir.beigirad.remote.service.RemoteServiceFactory

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideCountryService(application: Application): RemoteService {
            return RemoteServiceFactory().provideService(application, BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindRemoteService(countryRemote: CountryRemoteImpl): CountryRemote
}