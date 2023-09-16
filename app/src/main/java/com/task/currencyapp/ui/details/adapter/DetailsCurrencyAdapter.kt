package com.task.currencyapp.ui.details.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.currencyapp.R
import com.task.currencyapp.data.model.CurrencyRatesListItem
import kotlinx.android.synthetic.main.item_currency_details.view.*

class DetailsCurrencyAdapter(private val currencyRatesList: ArrayList<CurrencyRatesListItem>) :
    RecyclerView.Adapter<DetailsCurrencyAdapter.HistoryViewHolder>() {

    override fun getItemCount() = currencyRatesList.size

    fun addAll(_currencyRatesList: ArrayList<CurrencyRatesListItem>) {
        currencyRatesList.clear()
        currencyRatesList.addAll(_currencyRatesList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_currency_details, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(currencyRatesList[position])
    }

    inner class HistoryViewHolder(val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(currencyRatesListItem: CurrencyRatesListItem) {
            rootView.tvDay.text = currencyRatesListItem.day
            rootView.tvFrom.text = currencyRatesListItem.currencyCodeFrom
            rootView.tvTo.text = currencyRatesListItem.currencyCodeTo
            rootView.tvRate.text = currencyRatesListItem.exchangeRate
        }
    }
}
