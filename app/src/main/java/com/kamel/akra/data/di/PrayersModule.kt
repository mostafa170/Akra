package com.kamel.akra.data.di

import android.app.Application
import com.kamel.akra.data.datasources.PrayerDataSources
import com.kamel.akra.data.datasources.PrayerDataSourcesImpl
import com.kamel.akra.data.repositories.PrayersRepositoryImpl
import com.kamel.akra.domain.repositories.PrayersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PrayersModule {

    @Singleton
    @Provides
    fun provideDatasource(dispatcher: CoroutineDispatcher, context: Application): PrayerDataSources {
        return PrayerDataSourcesImpl(dispatcher,context)
    }

    @Singleton
    @Provides
    fun provideRepository(prayerDataSources: PrayerDataSources): PrayersRepository {
        return PrayersRepositoryImpl(prayerDataSources)
    }
}