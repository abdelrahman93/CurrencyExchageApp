package com.task.currencyapp.di.module

import com.task.currencyapp.domain.repository.Repository
import com.task.currencyapp.domain.usecase.exchange.ExchangeCurrencyUseCase
import com.task.currencyapp.domain.usecase.exchange.ExchangeCurrencyUseCaseImp
import dagger.Module
import dagger.Provides

@Module
class UsecaseModule {

    @Provides
    fun provideOfferUseCase(repository: Repository): ExchangeCurrencyUseCase {
        return ExchangeCurrencyUseCaseImp(repository = repository)
    }
}
