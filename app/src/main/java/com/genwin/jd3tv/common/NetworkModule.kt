package com.genwin.jd3tv.common

import com.genwin.jd3tv.BuildConfig
import com.genwin.jd3tv.screens.home.domain.interfaces.api.HomeApiService
import com.genwin.jd3tv.screens.splash.domain.home.ApiService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

//
// Created by Dina Mounib on 6/27/22.
//
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {



  @Provides
  @Singleton
  fun provideApiService(retrofit: Retrofit): HomeApiService = retrofit.create(HomeApiService::class.java)

    @Provides
    @Singleton
    fun loggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    fun okHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .cache(null)
            .build()
    }

    @Provides
    @Singleton
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))//GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}