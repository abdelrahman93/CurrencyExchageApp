package com.task.currencyapp.ui.currencyexchange

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.task.currencyapp.BaseFragment
import com.task.currencyapp.R
import com.task.currencyapp.base.BaseViewState
import com.task.currencyapp.data.model.CurrencyRatesList
import com.task.currencyapp.data.model.Rate
import com.task.currencyapp.data.model.Rates
import com.task.currencyapp.di.component.DaggerAppComponent
import com.task.currencyapp.ui.currencyexchange.adapter.CustomSpinnerAdapter
import kotlinx.android.synthetic.main.fragment_exchange_currency.*

class CurrencyExchangeFragment :
    BaseFragment<CurrencyExchangeViewModel>(CurrencyExchangeViewModel::class.java) {

    private var exchangeFromRate = 0.0
    private var exchangeToRate = 0.0
    private var exchangeFromCurrencyCode = ""
    private var exchangeToCurrencyCode = ""

    private var arrayRatesLatest = ArrayList<Rate>()

    private var convertCurrencyFrom = true

    override fun injectDagger() = DaggerAppComponent.factory()
        .create(requireContext())
        .inject(this)

    override fun getLayout() = R.layout.fragment_exchange_currency

    override fun initView() {
        etValueFrom.addTextChangedListener(textWatcherFrom)
        etValueTo.addTextChangedListener(textWatcherTo)
        btnSwap.setOnClickListener {
            swapButtonClicked()
        }
        viewModel.getCurrencyRates()

        btnDetails.setOnClickListener {
            val symbols = "$exchangeFromCurrencyCode,$exchangeToCurrencyCode"
            viewModel.getHistoricalCurrencyRatesForThreeLastDays(symbols.toUpperCase())
        }
    }

    override fun renderView(viewState: BaseViewState?) {
        when (viewState) {
            is CurrencyExchangeViewState.SuccessLatestState -> {
                setupSpinnersFromAndTo(viewState.response.rates)
            }

            is CurrencyExchangeViewState.ErrorLatestState -> {
                Toast.makeText(
                    activity,
                    getString(R.string.generic_error),
                    Toast.LENGTH_LONG
                ).show()
            }

            is CurrencyExchangeViewState.SuccessHistoricalState -> {
                val otherCurrenciesRatesList = viewModel.initOtherCurrenciesList(listRates = arrayRatesLatest, currencyCodeFrom = exchangeFromCurrencyCode)
                navigateToDetailsScreen(viewModel.initCurrencyHistoricalList(historicalCurrencyResponse = viewState.response, currencyCodeFrom = exchangeFromCurrencyCode, currencyCodeTo = exchangeToCurrencyCode), otherCurrenciesRatesList)
            }

            is CurrencyExchangeViewState.ErrorHistoricalState -> {
                Toast.makeText(
                    activity,
                    getString(R.string.generic_error),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun navigateToDetailsScreen(historyCurrencyRatesList: CurrencyRatesList, otherCurrenciesRatesList: CurrencyRatesList) {
        val action = CurrencyExchangeFragmentDirections.toDetailsCurrencyFragment(historyCurrencyRatesList, otherCurrenciesRatesList)
        findNavController().navigate(action)
    }

    private fun setupSpinnersFromAndTo(rates: Rates) {
        val ratesArray = viewModel.convertRatesToArrayList(rates)
        arrayRatesLatest = ratesArray
        val adapter = activity?.let { CustomSpinnerAdapter(it, ratesArray) }
        spFromCurrencies.adapter = adapter
        spToCurrencies.adapter = adapter

        // Default value to set EGP
        spToCurrencies.setSelection(3)

        spFromCurrencies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Get the selected Rate object
                val selectedFromRate = ratesArray[position]
                // Handle the selected rate here
                exchangeFromRate = selectedFromRate.exchangeRate
                exchangeFromCurrencyCode = selectedFromRate.currencyCode
                setupValueTo()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Handle the case where nothing is selected (optional)
            }
        }

        spToCurrencies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Get the selected Rate object
                val selectedToRate = ratesArray[position]
                // Handle the selected rate here
                exchangeToRate = selectedToRate.exchangeRate
                exchangeToCurrencyCode = selectedToRate.currencyCode
                setupValueTo()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Handle the case where nothing is selected (optional)
            }
        }
    }

    fun setupValueTo() {
        // From Endpoint Base is EUR to All Currencies rate
        // Equation = (rateCurrencyTo / rateCurrencyFrom) * numbers of [CurrencyFrom]

        if (etValueFrom.text.isNullOrEmpty().not()) {
            val fromFieldNumbers = etValueFrom.text.toString().toDouble()
            val result = (exchangeToRate / exchangeFromRate) * fromFieldNumbers
            val formattedValue = String.format("%.2f", result)
            etValueTo.setText(formattedValue)
        }
    }

    fun setupValueFrom() {
        if (etValueTo.text.isNullOrEmpty().not()) {
            val toFieldNumbers = etValueTo.text.toString().toDouble()
            val result = (exchangeFromRate / exchangeToRate) * toFieldNumbers
            val formattedValue = String.format("%.2f", result)
            etValueFrom.setText(formattedValue)
        }
    }

    private val textWatcherFrom = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // pass
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            convertCurrencyFrom = etValueFrom.isInputMethodTarget
            btnDetails.isEnabled =
                (etValueFrom.text?.isNotEmpty() == true) && (etValueTo.text?.isNotEmpty() == true)
        }

        override fun afterTextChanged(s: Editable?) {
            if (convertCurrencyFrom) {
                setupValueTo()
            }
        }
    }

    private val textWatcherTo = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // pass
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            convertCurrencyFrom = !etValueTo.isInputMethodTarget
            btnDetails.isEnabled =
                (etValueFrom.text?.isNotEmpty() == true) && (etValueTo.text?.isNotEmpty() == true)
        }

        override fun afterTextChanged(s: Editable?) {
            if (!convertCurrencyFrom) {
                setupValueFrom()
            }
        }
    }

    private fun swapButtonClicked() {
        val spFromCurrenciesPosition = spFromCurrencies.selectedItemPosition
        val spToCurrenciesPosition = spToCurrencies.selectedItemPosition
        spFromCurrencies.setSelection(spToCurrenciesPosition)
        spToCurrencies.setSelection(spFromCurrenciesPosition)
    }
}
