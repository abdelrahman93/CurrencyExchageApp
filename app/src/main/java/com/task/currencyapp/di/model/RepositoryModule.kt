package com.task.currencyapp.di.model

import com.task.currencyapp.data.repository.RepositoryImp
import com.task.currencyapp.domain.remote.ExchangeCurrencyAPI
import com.task.currencyapp.domain.repository.Repository
import com.task.currencyapp.network.Services
import com.task.currencyapp.utilities.ExchangeCurrenciesConstants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun createRetrofit(): Retrofit = Services.getInstance(BASE_URL).retrofit

    @Provides
    fun provideApi(retrofit: Retrofit): ExchangeCurrencyAPI {
        return retrofit.create(ExchangeCurrencyAPI::class.java)
    }

    @Provides
    fun provideRepository(exchangeCurrencyAPI: ExchangeCurrencyAPI): Repository {
        return RepositoryImp(exchangeCurrencyAPI = exchangeCurrencyAPI)
    }
}
