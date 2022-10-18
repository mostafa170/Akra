package com.kamel.akra.data.di

import com.kamel.akra.data.datasources.AzkarDataSources
import com.kamel.akra.data.datasources.AzkarDataSourcesImpl
import com.kamel.akra.data.repositories.AzkarRepositoryImpl
import com.kamel.akra.domain.repositories.AzkarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AzkarModule {

    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher): AzkarDataSources {
        return AzkarDataSourcesImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(azkarDataSources: AzkarDataSources): AzkarRepository {
        return AzkarRepositoryImpl(azkarDataSources)
    }
}