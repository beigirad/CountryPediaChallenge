package ir.beigirad.domain.interactor

import io.reactivex.Observable
import ir.beigirad.domain.executor.PostExecutionThread
import ir.beigirad.domain.model.Country
import ir.beigirad.domain.repository.CountryRepository

class GetCountries(
    private val countryRepository: CountryRepository,
    postExecutionThread: PostExecutionThread
) :
    ObservableUseCase<List<Country>, Nothing>(postExecutionThread) {
    override fun buildUseCaseObservable(params: Nothing?): Observable<List<Country>> {
        return countryRepository.getCountries()
    }
}