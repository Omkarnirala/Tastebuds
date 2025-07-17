package com.example.tastebuds.di.serverModule

import android.content.Context
import com.example.tastebuds.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApiService(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): ApiService {
        return remoteDataSource.buildApi(ApiService::class.java,context)
    }

}