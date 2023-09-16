package com.task.currencyapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CurrencyRatesList : ArrayList<CurrencyRatesListItem>(), Parcelable
