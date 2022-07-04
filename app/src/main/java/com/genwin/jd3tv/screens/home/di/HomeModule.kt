package com.genwin.jd3tv.screens.home.di

import com.genwin.jd3tv.screens.home.data.HomeRepositoryImpl
import com.genwin.jd3tv.screens.home.data.RemoteDataSourceImpl
import com.genwin.jd3tv.screens.home.domain.interfaces.HomeRepository
import com.genwin.jd3tv.screens.home.domain.interfaces.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class HomeModule {
  @Provides
  fun bindRepo(repository: HomeRepositoryImpl): HomeRepository = repository

  @Provides
  fun providesDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource = remoteDataSourceImpl

}