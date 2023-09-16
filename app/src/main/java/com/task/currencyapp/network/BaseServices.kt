package com.task.currencyapp.network

import retrofit2.Retrofit

/***
 * BaseServices this class act with the factory design pattern
 * it take a parameters and depend on them it decide to create retrofit using  httpRetrofit or HttpsRetrofit
 */
abstract class BaseServices {
    /***
     *
     * @return the Retrofit object which created above
     */
    var retrofit: Retrofit? = null
        protected set

    protected fun createRetrofit(baseUrl: String?) {
        retrofit = HttpRetrofit().getRetrofit(baseUrl)
    }
}
