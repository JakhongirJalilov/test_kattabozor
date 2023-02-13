package com.example.kattabozortest.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.kattabozortest.data.remote.repository.ProductRepository
import com.example.kattabozortest.data.remote.service.ProductService
import com.example.kattabozortest.domain.usecase.jokes.ProductUseCase
import com.example.kattabozortest.domain.usecase.jokes.ProductUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkhttp(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(ChuckerInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.kattabozor.uz/hh/test/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductService(
        retrofit: Retrofit
    ): ProductService {
        return retrofit.create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun providesProductRepository(
        productService: ProductService
    ): ProductRepository {
        return ProductRepository(productService)
    }

    @Provides
    @Singleton
    fun providesProductsUseCase(
        productRepository: ProductRepository
    ): ProductUseCase {
        return ProductUseCaseImpl(productRepository)
    }
}