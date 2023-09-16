package com.task.currencyapp.domain.repository

import com.task.currencyapp.data.model.CurrencyResponse
import io.reactivex.Observable

interface Repository {
    fun getCurrencyExchangeRates(): Observable<CurrencyResponse>
}
