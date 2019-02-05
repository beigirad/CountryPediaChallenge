package ir.beigirad.app.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ir.beigirad.app.CountryApplication
import ir.beigirad.app.di.module.ApplicationModule
import ir.beigirad.app.di.module.CacheModule
import ir.beigirad.app.di.module.RemoteModule
import ir.beigirad.app.di.module.UiModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
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