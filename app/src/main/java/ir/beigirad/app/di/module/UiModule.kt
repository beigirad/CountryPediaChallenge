package ir.beigirad.app.di.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.beigirad.app.di.UiThread
import ir.beigirad.app.features.countries.CountriesFragment
import ir.beigirad.domain.executor.PostExecutionThread

@Module
abstract class UiModule{
    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread


    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): CountriesFragment
}