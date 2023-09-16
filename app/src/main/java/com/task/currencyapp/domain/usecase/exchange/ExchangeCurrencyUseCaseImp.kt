package com.task.currencyapp.domain.usecase.exchange

import com.task.currencyapp.data.model.CurrencyResponse
import com.task.currencyapp.domain.repository.Repository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Abdelrahman Sherif on 15/09/2023.
 */
class ExchangeCurrencyUseCaseImp @Inject
constructor(val repository: Repository) : ExchangeCurrencyUseCase {

    override fun getCurrencyExchangeRates(): Observable<CurrencyResponse> {
        return repository.getCurrencyExchangeRates()
    }
}
