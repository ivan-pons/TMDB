package com.ipons.remotedatasource.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class LanguageHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("x-lang", Language_CODE)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val Language_CODE =
            "es" // FIXME debo coger el del sistema, esperaremos a tener el vasco
    }
}