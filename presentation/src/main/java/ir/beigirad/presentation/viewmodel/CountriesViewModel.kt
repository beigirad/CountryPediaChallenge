package ir.beigirad.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import ir.beigirad.domain.interactor.GetCountries
import ir.beigirad.domain.model.Country
import ir.beigirad.presentation.mapper.CountryViewMapper
import ir.beigirad.presentation.model.CountryView
import ir.beigirad.presentation.state.Resource
import ir.beigirad.presentation.state.ResourceState
import timber.log.Timber
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
        private val getCountries: GetCountries,
        private val countryMapper: CountryViewMapper
) : ViewModel() {

    private val countriesLiveData = MutableLiveData<Resource<List<CountryView>>>()

    override fun onCleared() {
        Timber.d("onCleared")
        getCountries.dispose()
        super.onCleared()
    }

    fun getCountries(): LiveData<Resource<List<CountryView>>> {
        return countriesLiveData
    }

    fun fetchCountries() {
        countriesLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getCountries.execute(CountriesObserver(), null)
    }

    private inner class CountriesObserver : DisposableObserver<List<Country>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Country>) {
            countriesLiveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { countryMapper.mapToView(it) }
                    , null))
        }

        override fun onError(e: Throwable) {
            countriesLiveData.postValue(
                Resource(
                    ResourceState.ERROR,
                    null,
                    e.localizedMessage
                )
            )
        }

    }

}