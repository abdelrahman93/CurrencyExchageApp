package com.task.currencyapp.data.repository

import com.task.currencyapp.data.model.CurrencyResponse
import com.task.currencyapp.domain.remote.ExchangeCurrencyAPI
import com.task.currencyapp.domain.repository.Repository
import com.task.currencyapp.utilities.ExchangeCurrenciesConstants.Companion.ACCESS_KEY_VALUE
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImp @Inject constructor(var exchangeCurrencyAPI: ExchangeCurrencyAPI) : Repository {
    override fun getCurrencyExchangeRates(): Observable<CurrencyResponse> {
        return exchangeCurrencyAPI.getCurrencyExchangeRates(
            ACCESS_KEY_VALUE
        )
    }

    override fun getHistoricalCurrencyExchangeRates(symbols: String,date :String): Observable<CurrencyResponse> {
        return exchangeCurrencyAPI.getHistoricalCurrencyExchangeRates(
            date,
            ACCESS_KEY_VALUE,
            symbols
        )
    }
}
