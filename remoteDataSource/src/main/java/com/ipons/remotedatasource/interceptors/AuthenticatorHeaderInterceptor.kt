package com.ipons.remotedatasource.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthenticatorHeaderInterceptor @Inject constructor(

) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (chain.request().headers[TOKEN_HEADER].isNullOrEmpty()) {
            builder.header(TOKEN_HEADER, USER_TOKEN)
        }
        return chain.proceed(builder.build())
    }

    companion object {
        private const val TOKEN_HEADER = "Authorization"
        private const val USER_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlZjE0NWMxMjNjNThjMDU3YjgwNGZkMzU5MmIxMjI1NiIsInN1YiI6IjY0OTE3YTFhMjYzNDYyMDE0ZTU5YTQyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.kqzGujvNgeZStjHp2fLK1eC6AUnsNVQDsWKReLlxx4w"
    }
}