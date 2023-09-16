package com.task.currencyapp.network

/***
 * this is singleton for the  services which used the default configuration
 * and in its constructor we call createRetrofit which decide which retrofit object must be used based on the params
 */
class Services : BaseServices {
    /***
     *
     * this constructor create retrofit object with the default values from build config
     */
    private constructor() {
        // createRetrofit(Buil.Base_Url);
    }

    /***
     *
     * @param baseUrl
     * this constructor create retrofit object with the received values from params
     */
    private constructor(baseUrl: String) {
        createRetrofit(baseUrl)
    }

    companion object {
        private var mInstance: Services? = null

        /***
         * @param baseUrl
         * @return return instance of services object which create retrofit
         * object with the received values from params with the received params
         */
        @Synchronized
        fun getInstance(baseUrl: String): Services? {
            if (mInstance == null) {
                mInstance = Services(baseUrl)
            }
            return mInstance
        }
    }
}
