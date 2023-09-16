package com.task.currencyapp.domain.usecase.exchange

import com.task.currencyapp.data.model.CurrencyResponse
import io.reactivex.Observable

/**
 * Created by Abdelrahman Sherif on 15/09/2023.
 */
interface ExchangeCurrencyUseCase {
    fun getCurrencyExchangeRates(): Observable<CurrencyResponse>
}
