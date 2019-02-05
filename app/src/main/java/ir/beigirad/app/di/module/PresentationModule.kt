package ir.beigirad.app.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ir.beigirad.app.di.ViewModelFactory
import ir.beigirad.presentation.viewmodel.CountriesViewModel
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindHomeViewModel(viewModel: CountriesViewModel): ViewModel

}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)