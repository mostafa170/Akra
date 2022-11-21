package com.kamel.akra.data.di

import com.kamel.akra.data.datasources.HadethDataSources
import com.kamel.akra.data.datasources.HadethDataSourcesImpl
import com.kamel.akra.data.repositories.HadethRepositoryImpl
import com.kamel.akra.domain.repositories.HadethRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class HadethModule {
    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher): HadethDataSources {
        return HadethDataSourcesImpl(dispatcher)
    }

    @Singleton
    @Provides
    fun provideRepository(hadethDataSources: HadethDataSources): HadethRepository {
        return HadethRepositoryImpl(hadethDataSources)
    }
}