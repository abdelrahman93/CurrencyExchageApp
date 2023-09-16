package com.task.currencyapp.domain.remote

import com.task.currencyapp.data.ApiUrl
import com.task.currencyapp.data.model.CurrencyResponse
import com.task.currencyapp.utilities.ExchangeCurrenciesConstants.Companion.ACCESS_KEY
import com.task.currencyapp.utilities.ExchangeCurrenciesConstants.Companion.DATE
import com.task.currencyapp.utilities.ExchangeCurrenciesConstants.Companion.SYMBOLS
import io.reactivex.Observable
import retrofit2.http.*

interface ExchangeCurrencyAPI {

    // "latest" endpoint - request the most recent exchange rate data
    @GET(ApiUrl.EXCHANGE_RATES_LATEST)
    fun getCurrencyExchangeRates(
        @Query(ACCESS_KEY) access_key: String
    ): Observable<CurrencyResponse>

    // "historical" endpoint - request historical rates for a specific day
    @GET(ApiUrl.EXCHANGE_RATES_DAY)
    fun getHistoricalCurrencyExchangeRates(
        @Path(DATE) date: String, // Dynamic date parameter
        @Query(ACCESS_KEY) access_key: String,
        @Query(SYMBOLS) symbols: String
    ): Observable<CurrencyResponse>
}
