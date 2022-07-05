package com.genwin.jd3tv.screens.splash.di

import com.genwin.jd3tv.screens.splash.data.RemoteDataSourceImpl
import com.genwin.jd3tv.screens.splash.data.SplashRepositoryImpl
import com.genwin.jd3tv.screens.splash.domain.interfaces.RemoteDataSource
import com.genwin.jd3tv.screens.splash.domain.interfaces.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class SplashModule {
  @Provides
  fun bindRepo(repository: SplashRepositoryImpl) : Repository = repository

  @Provides
  fun providesDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource = remoteDataSourceImpl

}