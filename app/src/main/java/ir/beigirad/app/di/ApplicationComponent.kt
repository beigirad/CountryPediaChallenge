package ir.beigirad.app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ir.beigirad.app.CountryApplication
import ir.beigirad.app.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
        DataModule::class,
        RemoteModule::class,
        CacheModule::class]
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent

    }

    fun inject(application: CountryApplication)

}