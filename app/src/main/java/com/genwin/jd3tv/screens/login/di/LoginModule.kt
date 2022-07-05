package com.genwin.jd3tv.screens.login.di

import com.genwin.jd3tv.screens.login.data.LoginRepositoryImpl
import com.genwin.jd3tv.screens.login.data.RemoteDataSourceImpl
import com.genwin.jd3tv.screens.login.domain.interfaces.LoginRepository
import com.genwin.jd3tv.screens.login.domain.interfaces.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

//
// Created by Dina Mounib on 7/5/22.
//
@Module
@InstallIn(SingletonComponent::class)
class LoginModule {
    @Provides
    fun bindRepo(repository: LoginRepositoryImpl): LoginRepository = repository

    @Provides
    fun providesDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource = remoteDataSourceImpl

}