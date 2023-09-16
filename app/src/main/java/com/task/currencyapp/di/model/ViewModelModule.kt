package com.task.currencyapp.di.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.currencyapp.di.factory.ViewModelFactory
import com.task.currencyapp.di.keys.ViewModelKey
import com.task.currencyapp.ui.currencyexchange.CurrencyExchangeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurrencyExchangeViewModel::class)
    abstract fun bindCurrencyExchangeViewModel(currencyExchangeViewModel: CurrencyExchangeViewModel): ViewModel
}
