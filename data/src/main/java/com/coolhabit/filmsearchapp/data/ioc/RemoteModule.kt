package com.coolhabit.filmsearchapp.data.ioc

import com.coolhabit.filmsearchapp.data.network.FilmsApi
import com.coolhabit.filmsearchapp.data.network.utils.API
import com.coolhabit.filmsearchapp.data.network.utils.ApiConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val APIKEY = "apikey"
private const val TIMEOUT = 30L

@Module
class RemoteModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .callTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val defaultRequest = chain.request()
            val defaultHttpUrl = defaultRequest.url
            val httpUrl = defaultHttpUrl.newBuilder()
                .addQueryParameter(APIKEY, API.PRIVATE_KEY)
                .build()

            val requestBuilder = defaultRequest.newBuilder().url(httpUrl)

            chain.proceed(requestBuilder.build())
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ApiConstants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideFilmsApi(retrofit: Retrofit): FilmsApi = retrofit.create(FilmsApi::class.java)
}
