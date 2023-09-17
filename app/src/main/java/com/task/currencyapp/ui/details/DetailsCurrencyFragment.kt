package com.task.currencyapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.task.currencyapp.R
import com.task.currencyapp.ui.details.adapter.DetailsCurrencyAdapter
import com.task.currencyapp.ui.details.adapter.OthersCurrencyAdapter
import kotlinx.android.synthetic.main.fragment_currency_details.rvCurrencyList
import kotlinx.android.synthetic.main.fragment_currency_details.rvCurrencyListOthers

class DetailsCurrencyFragment : Fragment() {

    private val args: DetailsCurrencyFragmentArgs by navArgs()

    private lateinit var adapterHistorical: DetailsCurrencyAdapter
    private lateinit var adapterOtherCurrencies: OthersCurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_currency_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        // setup Historical Currency
        adapterHistorical = DetailsCurrencyAdapter(ArrayList())
        rvCurrencyList.adapter = adapterHistorical
        adapterHistorical.addAll(args.ratesList)

        // setup Historical Currency
        adapterOtherCurrencies = OthersCurrencyAdapter(ArrayList())
        rvCurrencyListOthers.adapter = adapterOtherCurrencies
        adapterOtherCurrencies.addAll(args.ratesOthersList)
    }
}
