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
                internalState.value = CurrencyExchangeViewState.ErrorLatestState(errorMsg.message.toString())
            },
            success = io.reactivex.functions.Consumer { response ->
                internalState.value = CurrencyExchangeViewState.SuccessLatestState(response)
            }

        )
    }

    fun getHistoricalCurrencyRatesForThreeLastDays(symbols: String) {
        subscribe(
            request = exchangeCurrencyUseCase.fetchCurrencyRatesThreeTimes(symbols),
            loader = Loader.Progress(true),
            error = io.reactivex.functions.Consumer { errorMsg ->
                internalState.value = CurrencyExchangeViewState.ErrorHistoricalState(errorMsg.message.toString())
            },
            success = io.reactivex.functions.Consumer { response ->
                internalState.value = CurrencyExchangeViewState.SuccessHistoricalState(response)
            }

        )
    }

    fun initCurrencyHistoricalList(historicalCurrencyResponse: List<CurrencyResponse>, currencyCodeFrom: String, currencyCodeTo: String): CurrencyRatesList {
        val list = CurrencyRatesList()
        val sortedList = historicalCurrencyResponse.sortedByDescending { it.date }

        for (item in sortedList) {
            list.add(mapCurrencyResponseToUI(item, currencyCodeFrom, currencyCodeTo))
        }
        return list
    }

    fun initOtherCurrenciesList(listRates: ArrayList<Rate>, currencyCodeFrom: String): CurrencyRatesList {
        val list = CurrencyRatesList()
        val otherCurrencyArray = listOf("USD", "EUR", "SAR", "EGP", "CZK", "JPY", "KES", "AED", "SBD", "QAR")
        for (item in otherCurrencyArray) {
            if ((currencyCodeFrom == item).not()) {
                list.add(mapOthersCurrencyResponseToUI(listRates, currencyCodeFrom, item))
            }
        }
        return list
    }

    // Convert the Rates data class into an ArrayList of Rate objects
    fun convertRatesToArrayList(rates: Rates): ArrayList<Rate> {
        val rateList = ArrayList<Rate>()

        // Use reflection to iterate through the fields of Rates and add them to the list
        val properties = Rates::class.memberProperties

        for (property in properties) {
            val fieldName = property.name.toUpperCase()
            val fieldValue = property.get(rates) as Double
            if (fieldValue != 0.0) {
                rateList.add(Rate(fieldName, fieldValue))
            }
        }

        return rateList
    }

    private fun mapCurrencyResponseToUI(currencyResponse: CurrencyResponse, currencyCodeFrom: String, currencyCodeTo: String): CurrencyRatesListItem {
        val arrayRates = convertRatesToArrayList(currencyResponse.rates)
        val result = calculateCurrencyRate(arrayRates, currencyCodeFrom, currencyCodeTo)

        return CurrencyRatesListItem(
            exchangeRate = result,
            day = currencyResponse.date,
            currencyCodeFrom = "1 $currencyCodeFrom",
            currencyCodeTo = "$currencyCodeTo"
        )
    }

    private fun mapOthersCurrencyResponseToUI(listRates: ArrayList<Rate>, currencyCodeFrom: String, currencyCodeTo: String): CurrencyRatesListItem {
        val result = calculateCurrencyRate(listRates, currencyCodeFrom, currencyCodeTo)

        return CurrencyRatesListItem(
            exchangeRate = result,
            currencyCodeFrom = "1 $currencyCodeFrom",
            currencyCodeTo = "$currencyCodeTo"
        )
    }

    private fun calculateCurrencyRate(listRates: ArrayList<Rate>, currencyCodeFrom: String, currencyCodeTo: String): String {
        val rateFrom = (listRates.find { it.currencyCode == currencyCodeFrom })
        val rateTo = listRates.find { it.currencyCode == currencyCodeTo }
        val rate = (rateTo?.exchangeRate ?: 0.0) / (rateFrom?.exchangeRate ?: 0.0)
        val formattedValue = String.format("%.2f", rate)
        return formattedValue
    }
}
