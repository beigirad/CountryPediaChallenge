package ir.beigirad.app.features.countries

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.beigirad.app.R
import ir.beigirad.app.model.Country
import ir.beigirad.zeroapplication.bases.BaseVH
import ir.beigirad.zeroapplication.inflate
import kotlinx.android.synthetic.main.item_country.view.*
import javax.inject.Inject

class CountriesAdapter @Inject constructor() : RecyclerView.Adapter<BaseVH>() {

    var list = listOf<Country>()
    private var listener: CountrySelectListener? = null

    fun setListener(listener: CountrySelectListener?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseVH {
        return CountryVH(parent.inflate(R.layout.item_country))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BaseVH, position: Int) {
        holder.bind()
    }


    inner class CountryVH(itemView: View) : BaseVH(itemView) {
        val img = itemView.item_country_img
        val title = itemView.item_country_title
        val sub = itemView.item_country_sub

        init {
            itemView.setOnLongClickListener {
                listener?.onLongPressCountry(list[adapterPosition])
                true
            }
            itemView.setOnClickListener {
                listener?.onSelectCountry(list[adapterPosition])
            }
        }

        override fun bind() {
            list[adapterPosition].apply {
//                img.loadUrl(flagUrl)
                title.text = name
                sub.text = population.toString()
            }
        }


    }

}