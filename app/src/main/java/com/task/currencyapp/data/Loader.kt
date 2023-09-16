package com.task.currencyapp.data

import com.task.currencyapp.base.BaseViewState

sealed class Loader : BaseViewState() {
    data class Shimmer(var show: Boolean) : Loader()
    data class Progress(var show: Boolean) : Loader()
}


