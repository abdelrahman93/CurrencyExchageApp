package com.task.currencyapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/***
 * used to build retrofit builder without ssl pinning
 */
class HttpRetrofit {
    /***
     *
     * @param baseUrl
     * @return Retrofit using the below method
     */
    fun getRetrofit(baseUrl: String): Retrofit {
        return buildRetrofit(baseUrl)
    }

    /***
     *
     * @param baseUrl
     * @return Retrofit object first it depend on the RetrofitBuilder which is in the parent class BaseRetrofit
     * and we just add baseurl to it and build retrofit object
     */
    private fun buildRetrofit(baseUrl: String): Retrofit {
        return retrofitBuilder.baseUrl(baseUrl).client(OkHttpClient.Builder().build()).build()
    }

    protected val retrofitBuilder: Retrofit.Builder
        protected get() = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
}
