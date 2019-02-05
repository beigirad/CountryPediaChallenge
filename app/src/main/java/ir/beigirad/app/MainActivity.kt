package ir.beigirad.app

import androidx.fragment.app.Fragment
import dagger.android.AndroidInjection
import ir.beigirad.app.features.countries.CountriesFragment
import ir.beigirad.app.model.Country
import ir.beigirad.zeroapplication.bases.BaseActivity
import ir.beigirad.zeroapplication.toast

/**
 * Created by farhad-mbp on 3/18/18.
 */
class MainActivity : BaseActivity() ,INavigationListener{
    override fun onSelectedCountry(country: Country) {
        toast(country.toString())
    }

    override val contentView: Int
        get() = R.layout.activity_main

    override val hasBackConfirmation: Boolean
        get() = true


    override fun initVariables() {
        super.initVariables()
    }

    override fun initUI() {
        super.initUI()
        showFragment(CountriesFragment.newInstance())
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
            .addToBackStack(fragment.javaClass.simpleName)
            .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }


}