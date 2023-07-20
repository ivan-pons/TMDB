package com.ipons.remotedatasource.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class LanguageHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val urlWithQueryParam = originalRequest.url.newBuilder()
            .addQueryParameter("language", Language_CODE)
            .build()
        val newRequest = originalRequest.newBuilder()
            .url(urlWithQueryParam)
            .build()
        return chain.proceed(newRequest)
    }

    companion object {
        private const val Language_CODE = "es"
    }
}