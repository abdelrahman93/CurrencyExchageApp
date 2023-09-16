package com.task.currencyapp.di.component

import android.content.Context
import com.task.currencyapp.di.model.RepositoryModule
import com.task.currencyapp.di.model.SCHEDULER_IO
import com.task.currencyapp.di.model.SCHEDULER_MAIN_THREAD
import com.task.currencyapp.di.model.SchedulerModule
import com.task.currencyapp.di.model.ViewModelModule
import com.task.currencyapp.di.module.*
import com.task.currencyapp.ui.currencyexchange.CurrencyExchangeFragment
import dagger.BindsInstance
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        SchedulerModule::class,
        UsecaseModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(ingredientsFragment: CurrencyExchangeFragment)

    @Named(SCHEDULER_MAIN_THREAD)
    fun getMainThread(): Scheduler

    @Named(SCHEDULER_IO)
    fun getIOThread(): Scheduler
}
