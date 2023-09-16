package com.task.currencyapp.data.model

import com.google.gson.annotations.SerializedName

// Define the Rate data class
data class Rate(
    val currencyCode: String,
    val exchangeRate: Double
)

data class Rates(

    @SerializedName("AED") val aED: Double,
    @SerializedName("AUD") val aUD: Double,
    @SerializedName("CZK") val cZK: Double,
    @SerializedName("EGP") val eGP: Double,
    @SerializedName("EUR") val eUR: Double,
    @SerializedName("JPY") val jPY: Double,
    @SerializedName("KES") val kES: Double,
    @SerializedName("QAR") val qAR: Double,
    @SerializedName("SAR") val sAR: Double,
    @SerializedName("SBD") val sBD: Double,
    @SerializedName("SCR") val sCR: Double,
    @SerializedName("SDG") val sDG: Double,
    @SerializedName("SEK") val sEK: Double,
    @SerializedName("SGD") val sGD: Double,
    @SerializedName("SHP") val sHP: Double,
    @SerializedName("SLE") val sLE: Double,
    @SerializedName("SLL") val sLL: Double,
    @SerializedName("SOS") val sOS: Double,
    @SerializedName("SSP") val sSP: Double,
    @SerializedName("SRD") val sRD: Double,
    @SerializedName("STD") val sTD: Double,
    @SerializedName("SYP") val sYP: Double,
    @SerializedName("SZL") val sZL: Double,
    @SerializedName("THB") val tHB: Double,
    @SerializedName("TJS") val tJS: Double,
    @SerializedName("TMT") val tMT: Double,
    @SerializedName("TND") val tND: Double,
    @SerializedName("TOP") val tOP: Double,
    @SerializedName("TRY") val tRY: Double,
    @SerializedName("TTD") val tTD: Double,
    @SerializedName("TWD") val tWD: Double,
    @SerializedName("TZS") val tZS: Double,
    @SerializedName("UAH") val uAH: Double,
    @SerializedName("UGX") val uGX: Double,
    @SerializedName("USD") val uSD: Double,
    @SerializedName("UYU") val uYU: Double,
    @SerializedName("UZS") val uZS: Double,
    @SerializedName("VEF") val vEF: Double,
    @SerializedName("VES") val vES: Double,
    @SerializedName("VND") val vND: Double,
    @SerializedName("VUV") val vUV: Double,
    @SerializedName("WST") val wST: Double,
    @SerializedName("XAF") val xAF: Double,
    @SerializedName("XAG") val xAG: Double,
    @SerializedName("XAU") val xAU: Double,
    @SerializedName("XCD") val xCD: Double,
    @SerializedName("XDR") val xDR: Double,
    @SerializedName("XOF") val xOF: Double,
    @SerializedName("XPF") val xPF: Double,
    @SerializedName("YER") val yER: Double,
    @SerializedName("ZAR") val zAR: Double,
    @SerializedName("ZMK") val zMK: Double,
    @SerializedName("ZMW") val zMW: Double,
    @SerializedName("ZWL") val zWL: Double
)


