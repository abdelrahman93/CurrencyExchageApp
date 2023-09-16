package com.task.currencyapp.ui.currencyexchange

import com.task.currencyapp.base.BaseViewState
import com.task.currencyapp.data.model.CurrencyResponse

/**
 * Created by Abdelrahman Sherif on 15/09/2023.
 */
class CurrencyExchangeViewState : BaseViewState() {

    class SuccessLatestState(val response: CurrencyResponse) : BaseViewState()
    class ErrorLatestState(val msg: String) : BaseViewState()

    class SuccessHistoricalState(val response: List<CurrencyResponse>) : BaseViewState()
    class ErrorHistoricalState(val msg: String) : BaseViewState()
}
