package ir.beigirad.app.features.detail

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import ir.beigirad.app.R
import ir.beigirad.app.model.Country
import ir.beigirad.zeroapplication.bases.BaseFragment
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailFragment : BaseFragment() {

    // This class created just for having 2 fragment :)

    override val childView: Int
        get() = R.layout.fragment_detail

    override val toolbar: Toolbar?
        get() = app_toolbar
    override val toolbarTitle: Int?
        get() = R.string.countries_list

    companion object {
        private const val ARG_COUNTRY = "COUNTRY"
        fun newInstance(country: Country): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_COUNTRY, country)
                }
            }
        }
    }

    private var country: Country? = null

    override fun initVariables() {
        super.initVariables()
        country = arguments?.getParcelable(ARG_COUNTRY)
    }


    override fun initUI() {
        super.initUI()
        country?.apply {
            detail_title.text = name
            detail_sub.text = population.toString()
        }
    }


}