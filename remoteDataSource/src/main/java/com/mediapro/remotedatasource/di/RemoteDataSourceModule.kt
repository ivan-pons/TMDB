package com.mediapro.remotedatasource.di

import com.mediapro.remotedatasource.apis.CoreApi
import com.mediapro.remotedatasource.interceptors.AuthenticatorHeaderInterceptor
import com.mediapro.remotedatasource.interceptors.LanguageHeaderInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideOkHttpClientBuilder(
        authenticatorHeaderInterceptor: AuthenticatorHeaderInterceptor
    ): OkHttpClient.Builder {
        val okHttpClientBuilder = OkHttpClient.Builder()
//        if (BuildConfig.DEBUG) {
//            // Only for not mocked mode
//            val logInterceptor = HttpLoggingInterceptor()
//            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
//            okHttpClientBuilder.addInterceptor(logInterceptor)
//        }
        okHttpClientBuilder.addInterceptor(LanguageHeaderInterceptor())
        okHttpClientBuilder.addInterceptor(authenticatorHeaderInterceptor)
        return okHttpClientBuilder
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideCoreApi(
        okHttpClientBuilder: OkHttpClient.Builder,
        moshi: Moshi
    ): CoreApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pre.caixaforumplus.org") //FIXME hay ver como nos llegará
            .client(okHttpClientBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()

        return retrofit.create(CoreApi::class.java)
    }

}