package com.technicaltest.stubhub.di

import com.technicaltest.stubhub.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.Date
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl("https://gateway.marvel.com")
        .client(okHttpClient)
        .build()

    @Provides
    fun providesOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named("apiKeyInterceptor") apiKeyInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Named("apiKeyInterceptor")
    fun providesApiKeyInterceptor(): Interceptor = Interceptor {
        val original = it.request()
        val originalHttpUrl = original.url

        val ts = Date().time.toString()

        val md = MessageDigest.getInstance("MD5")
        val hash =
            BigInteger(1, md.digest("$ts${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}".toByteArray())).toString(16).padStart(32, '0')

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", BuildConfig.API_KEY)
            .addQueryParameter("hash", hash)
            .build()
        val requestBuilder = original.newBuilder().url(url)

        val request = requestBuilder.build()
        it.proceed(request)
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}