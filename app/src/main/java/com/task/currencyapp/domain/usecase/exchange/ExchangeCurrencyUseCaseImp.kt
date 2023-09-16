package com.task.currencyapp.domain.usecase.exchange

import android.util.Log
import com.task.currencyapp.data.model.CurrencyResponse
import com.task.currencyapp.domain.repository.Repository
import com.task.currencyapp.utilities.HelperDates
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Abdelrahman Sherif on 15/09/2023.
 */
class ExchangeCurrencyUseCaseImp @Inject
constructor(val repository: Repository) : ExchangeCurrencyUseCase {

    override fun getCurrencyExchangeRates(): Observable<CurrencyResponse> {
        return repository.getCurrencyExchangeRates()
    }

    override fun getHistoricalCurrencyExchangeRates(
        symbols: String,
        index: Int
    ): Observable<CurrencyResponse> {
        val today = HelperDates().getCurrentDate()
        val yesterday = HelperDates().getYesterdayDate()
        val twoDaysAgo = HelperDates().getTwoDaysAgoDate()

        val date = when (index) {
            0 -> today
            1 -> yesterday
            2 -> twoDaysAgo
            else -> {
                today
            }
        }

        return repository.getHistoricalCurrencyExchangeRates(symbols, date)
    }

    // Call the getCurrencyRate function three times (today-yesterday-2 days ago)  and collect the responses into a list
    // As the End point with range date (From - To )  is not free
    override fun fetchCurrencyRatesThreeTimes(symbols: String): Observable<List<CurrencyResponse>> {
        return Observable
            .range(0, 3) // Create an observable for 3 iterations
            .flatMap { index ->
                getHistoricalCurrencyExchangeRates(symbols, index).subscribeOn(
                    Schedulers.io()
                )
            }
            .toList()
            .toObservable()
    }
}
