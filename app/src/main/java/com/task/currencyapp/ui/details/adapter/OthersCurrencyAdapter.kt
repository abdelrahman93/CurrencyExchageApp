package com.task.currencyapp.ui.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.currencyapp.R
import com.task.currencyapp.data.model.CurrencyRatesListItem
import kotlinx.android.synthetic.main.item_currency_details.view.*

class OthersCurrencyAdapter(private val currencyRatesList: ArrayList<CurrencyRatesListItem>) :
    RecyclerView.Adapter<OthersCurrencyAdapter.OtherCurrenciesViewHolder>() {

    override fun getItemCount() = currencyRatesList.size

    fun addAll(_currencyRatesList: ArrayList<CurrencyRatesListItem>) {
        currencyRatesList.clear()
        currencyRatesList.addAll(_currencyRatesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherCurrenciesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_other_currencies_details, parent, false)
        return OtherCurrenciesViewHolder(view)
    }

    override fun onBindViewHolder(holder: OtherCurrenciesViewHolder, position: Int) {
        holder.bind(currencyRatesList[position])
    }

    inner class OtherCurrenciesViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(currencyRatesListItem: CurrencyRatesListItem) {
            rootView.tvFrom.text = currencyRatesListItem.currencyCodeFrom
            rootView.tvTo.text = currencyRatesListItem.currencyCodeTo
            rootView.tvRate.text = currencyRatesListItem.exchangeRate
        }
    }
}
