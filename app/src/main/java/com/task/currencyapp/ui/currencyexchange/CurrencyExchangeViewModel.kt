package com.task.currencyapp.ui.currencyexchange

import com.task.currencyapp.BaseViewModel
import com.task.currencyapp.data.Loader
import com.task.currencyapp.data.model.CurrencyRatesList
import com.task.currencyapp.data.model.CurrencyRatesListItem
import com.task.currencyapp.data.model.CurrencyResponse
import com.task.currencyapp.data.model.Rate
import com.task.currencyapp.data.model.Rates
import com.task.currencyapp.di.model.SCHEDULER_IO
import com.task.currencyapp.di.model.SCHEDULER_MAIN_THREAD
import com.task.currencyapp.domain.usecase.exchange.ExchangeCurrencyUseCase
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named
import kotlin.reflect.full.memberProperties

/**
 * Created by Abdelrahman Sherif on 15/09/2023.
 */
class CurrencyExchangeViewModel @Inject constructor(
    private val exchangeCurrencyUseCase: ExchangeCurrencyUseCase,
    @Named(SCHEDULER_IO) private val schedulerIo: Scheduler,
    @Named(SCHEDULER_MAIN_THREAD) private val schedulerMain: Scheduler
) : BaseViewModel(schedulerIo, schedulerMain) {

    fun getCurrencyRates() {
        subscribe(
            request = exchangeCurrencyUseCase.getCurrencyExchangeRates(),
            loader = Loader.Progress(true),
            error = io.reactivex.functions.Consumer { errorMsg ->
                internalState.value = CurrencyExchangeViewState.ErrorState(errorMsg.message.toString())
            },
            success = io.reactivex.functions.Consumer { response ->
                internalState.value = CurrencyExchangeViewState.SuccessState(response)
            }

        )
    }
    fun initCurrencyHistoricalList(response: CurrencyResponse): CurrencyRatesList {
        val list = CurrencyRatesList()

        list.add(
            CurrencyRatesListItem(
                exchangeRate = "1.23",
                day = "Day 1",
                currencyCodeFrom = "AED",
                currencyCodeTo = "EGP"
            )
        )
        list.add(
            CurrencyRatesListItem(
                exchangeRate = "4.23",
                day = "Day 2",
                currencyCodeFrom = "US",
                currencyCodeTo = "EGP"
            )
        )

        list.add(
            CurrencyRatesListItem(
                exchangeRate = "3.23",
                day = "Day 3",
                currencyCodeFrom = "Euro",
                currencyCodeTo = "EGP"
            )
        )

        return list
    }

    // Convert the Rates data class into an ArrayList of Rate objects
    internal fun convertRatesToArrayList(rates: Rates): ArrayList<Rate> {
        val rateList = ArrayList<Rate>()

        // Use reflection to iterate through the fields of Rates and add them to the list
        val properties = Rates::class.memberProperties

        for (property in properties) {
            val fieldName = property.name.toUpperCase()
            val fieldValue = property.get(rates) as Double

            rateList.add(Rate(fieldName, fieldValue))
        }

        return rateList
    }
}
