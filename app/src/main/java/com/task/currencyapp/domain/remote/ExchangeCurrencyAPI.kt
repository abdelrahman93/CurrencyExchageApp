package com.task.currencyapp.domain.remote

import com.task.currencyapp.data.model.ApiUrl
import com.task.currencyapp.data.model.CurrencyResponse
import com.task.currencyapp.utilities.ExchangeCurrenciesConstants.Companion.ACCESS_KEY
import io.reactivex.Observable
import retrofit2.http.*

interface ExchangeCurrencyAPI {
    @GET(ApiUrl.EXCHANGE_RATES)
    fun getCurrencyExchangeRates(
        @Query(ACCESS_KEY) access_key: String
    ): Observable<CurrencyResponse>
}
