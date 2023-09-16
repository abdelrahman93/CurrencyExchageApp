package com.task.currencyapp.data.model

import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timestamp") val timestamp: Int,
    @SerializedName("base") val base: String,
    @SerializedName("date") val date: String,
    @SerializedName("rates") val rates: Rates
)
