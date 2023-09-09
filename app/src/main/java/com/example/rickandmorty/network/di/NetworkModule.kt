package com.example.rickandmorty.network.di

import android.annotation.SuppressLint
import android.content.Context
import com.example.rickandmorty.network.service.ApiRepository
import com.example.rickandmorty.network.model.ApiResponse
import com.example.rickandmorty.network.service.ApiService
import com.example.rickandmorty.utils.Constant
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Provides
  fun provideApi(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

  @Provides
  fun provideGson(): Gson {
    val gsonBuilder = GsonBuilder()
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    return gsonBuilder.create()
  }

  @Provides
  fun provideOkHttpClient(): OkHttpClient {
    val okHttpClientBuilder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()

    // Create a trust manager that does not validate certificate chains
    val trustAllCerts = arrayOf<TrustManager>(
      @SuppressLint("CustomX509TrustManager")
      object : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        @Throws(CertificateException::class) override fun checkClientTrusted(
          chain: Array<X509Certificate>,
          authType: String
        ) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(
          chain: Array<X509Certificate>,
          authType: String
        ) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
          return arrayOf()
        }
      }
    )


    // Install the all-trusting trust manager
    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(
      null,
      trustAllCerts, SecureRandom()
    )
    val sslSocketFactory = sslContext.socketFactory

    logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
    okHttpClientBuilder.addInterceptor(logging)
    okHttpClientBuilder
      .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
      .hostnameVerifier { _, _ -> true }
      .connectTimeout(Constant.REQUEST_TIMEOUT_SECONDS, SECONDS)
      .readTimeout(Constant.REQUEST_TIMEOUT_SECONDS, SECONDS)
      .writeTimeout(Constant.REQUEST_TIMEOUT_SECONDS, SECONDS)
      .retryOnConnectionFailure(true)


    return okHttpClientBuilder.build()
  }

  @Provides
  fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit
      .Builder()
      .baseUrl(Constant.BASE_URL.trim())
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
  }

  @Provides
  fun provideApiResponse(context: Context): ApiResponse {
    return ApiResponse(context)
  }

//  @Singleton
//  @Provides
//  fun provideApiRepository(apiResponse: ApiResponse, apiService: ApiService): ApiRepository {
//    return ApiRepository(apiService)
//  }
}