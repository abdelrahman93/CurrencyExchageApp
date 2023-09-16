package com.task.currencyapp.base

sealed class ErrorViewState : BaseViewState() {
    data class Error(val message: String?) : BaseViewState()
}
