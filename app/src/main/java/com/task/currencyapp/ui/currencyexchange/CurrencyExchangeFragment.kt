package com.task.currencyapp.ui.currencyexchange

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.navigation.fragment.findNavController
import com.task.currencyapp.BaseFragment
import com.task.currencyapp.R
import com.task.currencyapp.base.BaseViewState
import com.task.currencyapp.data.model.CurrencyRatesList
import com.task.currencyapp.data.model.Rates
import com.task.currencyapp.di.component.DaggerAppComponent
import com.task.currencyapp.ui.currencyexchange.adapter.CustomSpinnerAdapter
import kotlinx.android.synthetic.main.fragment_exchange_currency.*

class CurrencyExchangeFragment :
    BaseFragment<CurrencyExchangeViewModel>(CurrencyExchangeViewModel::class.java) {

    override fun injectDagger() = DaggerAppComponent.factory()
        .create(requireContext())
        .inject(this)

    override fun getLayout() = R.layout.fragment_exchange_currency

    override fun initView() {
        etValueFrom.addTextChangedListener(textWatcher)
        viewModel.getCurrencyRates()
    }

    override fun renderView(viewState: BaseViewState?) {
        when (viewState) {
            is CurrencyExchangeViewState.SuccessState -> {
                setupSpinnerFromAndTo(viewState.response.rates)

                btnDetails.setOnClickListener {
                    navigateToDetailsScreen(viewModel.initCurrencyHistoricalList(response = viewState.response))
                }
            }

            is CurrencyExchangeViewState.ErrorState -> {
                Log.i("TAG", "renderView:Error  " + viewState.msg)
            }
        }
    }

    private fun navigateToDetailsScreen(listCurrencyList: CurrencyRatesList) {
        val action = CurrencyExchangeFragmentDirections.toDetailsCurrencyFragment(listCurrencyList)
        findNavController().navigate(action)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // pass
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btnDetails.isEnabled = etValueFrom.text?.isNotEmpty() == true
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    private fun setupSpinnerFromAndTo(rates: Rates) {
        val ratesArray = viewModel.convertRatesToArrayList(rates)
        val adapter = activity?.let { CustomSpinnerAdapter(it, ratesArray) }
        spFromCurrencies.adapter = adapter
        spToCurrencies.adapter = adapter

        spFromCurrencies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Get the selected Rate object
                val selectedRate = ratesArray[position]

                // Handle the selected rate here
                val currencyCode = selectedRate.currencyCode
                val exchangeRate = selectedRate.exchangeRate
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Handle the case where nothing is selected (optional)
            }
        }
    }
}
