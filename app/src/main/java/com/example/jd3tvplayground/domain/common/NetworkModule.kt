package com.example.jd3tvplayground.domain.common

import android.content.Context
import com.example.jd3tvplayground.BuildConfig
import com.example.jd3tvplayground.domain.home.HomeService
import com.example.jd3tvplayground.domain.splash.ApiService
import com.example.jd3tvplayground.domain.splash.Repository
import com.example.jd3tvplayground.domain.splash.RepositoryImpl
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

//
// Created by Dina Mounib on 6/27/22.
//
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

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
    fun providesMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)//+"web-clients/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))//GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }
//    @Singleton
//    @Provides
//    @Named("Normal")
//    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {return Retrofit.Builder()
//        .baseUrl(BuildConfig.BASE_URL)
//        .addConverterFactory(MoshiConverterFactory.create(moshi))//GsonConverterFactory.create(gson))
//        .client(okHttpClient)
//        .build()
//    }
//
    @Singleton
    @Provides
    @Named("Home")
    fun provideHomeRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))//GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()
    }

    @Provides
    @Singleton
    fun getApiService( retrofit: Retrofit):ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun getHomeService(@Named("Home") retrofit: Retrofit):HomeService {
        return retrofit.create(HomeService::class.java)
    }
}