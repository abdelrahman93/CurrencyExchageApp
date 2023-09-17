package com.task.currencyapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrencyRatesListItem(
    var currencyCodeFrom: String?,
    var currencyCodeTo: String?,
    var exchangeRate: String?,
    var day: String? = ""
) : Parcelable
