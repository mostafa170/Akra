package com.kamel.akra.data.di

import android.app.Application
import com.kamel.akra.data.datasources.AzkarDataSources
import com.kamel.akra.data.datasources.AzkarDataSourcesImpl
import com.kamel.akra.data.datasources.QuranDataSources
import com.kamel.akra.data.datasources.QuranDataSourcesImpl
import com.kamel.akra.data.repositories.AzkarRepositoryImpl
import com.kamel.akra.data.repositories.QuranRepositoryImpl
import com.kamel.akra.domain.repositories.AzkarRepository
import com.kamel.akra.domain.repositories.QuranRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class QuranModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher): QuranDataSources {
        return QuranDataSourcesImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(quranDataSources: QuranDataSources): QuranRepository {
        return QuranRepositoryImpl(quranDataSources)
    }
}