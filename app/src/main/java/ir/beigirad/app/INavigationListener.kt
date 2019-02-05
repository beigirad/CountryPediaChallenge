package ir.beigirad.app

import ir.beigirad.app.model.Country

interface INavigationListener {
    fun onShowDetail(country: Country)
}