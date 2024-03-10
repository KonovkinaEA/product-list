package com.example.productlist.di

import com.example.productlist.data.Repository
import com.example.productlist.data.RepositoryImpl
import com.example.productlist.data.api.ApiService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Singleton
    @Binds
    fun provideRepository(repository: RepositoryImpl): Repository

    companion object {

        @Provides
        fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Singleton
        @Provides
        fun provideApiService(
            okHttpClient: OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
        ): ApiService {
            return Retrofit.Builder()
                .baseUrl("https://dummyjson.com")
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(ApiService::class.java)
        }

        @Singleton
        @Provides
        fun provideGsonConverterFactory(): GsonConverterFactory {
            return GsonConverterFactory.create()
        }

        @Singleton
        @Provides
        fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @Provides
        fun provideLoggingInterceptor(): HttpLoggingInterceptor {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }
}
