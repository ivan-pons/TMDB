package com.ipons.remotedatasource.interceptors

import com.ipons.localdatasource.utils.UserManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthenticatorHeaderInterceptor @Inject constructor(
    private val userManager: UserManager
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (chain.request().headers[TOKEN_HEADER].isNullOrEmpty()) {
            builder.header(TOKEN_HEADER, userManager.token)
            builder.header(PROFILE_HEADER, userManager.profileId)
        }

        return chain.proceed(builder.build())
    }

    companion object {
        private const val TOKEN_HEADER = "x-token"
        private const val PROFILE_HEADER = "x-Profileid"
    }

}