package ir.beigirad.app.features.countries

import android.content.Context
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import ir.beigirad.app.INavigationListener
import ir.beigirad.app.R
import ir.beigirad.app.di.ViewModelFactory
import ir.beigirad.app.mapper.CountryMapper
import ir.beigirad.app.model.Country
import ir.beigirad.app.widget.StateView
import ir.beigirad.presentation.model.CountryView
import ir.beigirad.presentation.state.Resource
import ir.beigirad.presentation.state.ResourceState
import ir.beigirad.presentation.viewmodel.CountriesViewModel
import ir.beigirad.zeroapplication.bases.BaseFragment
import ir.beigirad.zeroapplication.toast
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_countries.*
import timber.log.Timber
import javax.inject.Inject

class CountriesFragment : BaseFragment(), CountrySelectListener {
    override fun onLongPressCountry(selected: Country) {
        toast("Population : ${selected.population}")
    }

    override fun onSelectCountry(selected: Country) {
        listener?.onShowDetail(selected)
    }

    override val childView: Int
        get() = R.layout.fragment_counries

    override val toolbar: Toolbar?
        get() = app_toolbar
    override val toolbarTitle: Int?
        get() = R.string.countries_list

    companion object {
        fun newInstance(): CountriesFragment {
            return CountriesFragment()
        }
    }


    private var listener: INavigationListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is INavigationListener)
            listener = context
        else
            throw IllegalArgumentException("${context?.javaClass?.simpleName} did not implement INavigationListener")
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var viewModel: CountriesViewModel
    @Inject
    lateinit var mapper: CountryMapper
    @Inject
    lateinit var adapter: CountriesAdapter


    override fun initVariables() {
        super.initVariables()
        AndroidSupportInjection.inject(this)
        viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(CountriesViewModel::class.java)
    }


    override fun initUI() {
        super.initUI()
        adapter.setListener(this)
        countries_ry.adapter = adapter
        countries_ry.layoutManager = LinearLayoutManager(context)

        countries_ry_state.setRetryListener {
            viewModel.fetchCountries()
        }

        viewModel.getCountries().observe(this, Observer { handleData(it) })
        viewModel.fetchCountries()
    }

    private fun handleData(it: Resource<List<CountryView>>) {
        when (it.status) {
            ResourceState.SUCCESS -> {
                Timber.d("received ${it.data?.size} item")
                showCountries(it.data)

                if (it.data.orEmpty().isNotEmpty())
                    countries_ry_state.setState(StateView.State.SUCCESS)
                else
                    countries_ry_state.setState(StateView.State.BLANK)
            }
            ResourceState.LOADING -> {
                Timber.i("loading")
                countries_ry_state.setState(StateView.State.LOADING)
            }

            ResourceState.ERROR -> {
                Timber.i("error ${it.message}")
                countries_ry_state.setState(StateView.State.ERROR)
            }
        }
    }

    private fun showCountries(data: List<CountryView>?) {
        data?.let {
            adapter.list = it.map(mapper::mapToView).toMutableList()
            adapter.notifyDataSetChanged()
        }
    }

}