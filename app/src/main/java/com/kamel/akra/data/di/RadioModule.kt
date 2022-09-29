package com.kamel.akra.data.di

import com.kamel.akra.data.datasources.RadioDataSource
import com.kamel.akra.data.datasources.RadioDataSourceImpl
import com.kamel.akra.data.repositories.RadioRepositoryImpl
import com.kamel.akra.domain.repositories.RadioRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RadioModule {

    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher): RadioDataSource {
        return RadioDataSourceImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(radioDataSource: RadioDataSource): RadioRepository {
        return RadioRepositoryImpl(radioDataSource)
    }

}