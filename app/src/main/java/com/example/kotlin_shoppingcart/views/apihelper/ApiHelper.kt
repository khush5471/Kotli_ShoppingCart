package com.example.kotlin_shoppingcart.views.apihelper

import android.os.Build
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array.get
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

object ApiHelper {

    private val TIMEOUT = 30L
    private val mRetrofit: Retrofit
    private val mService: WebService
    private const val CONNECTION_TIME_OUT: Long = 60


    // Creating Retrofit Object
    init {
        mRetrofit = Retrofit.Builder()
//            .baseUrl("https://api.openweathermap.org/data/2.5/")
//            .baseUrl("https://10.0.2.2:443/API/")//169.254.186.174 // this url is for simulator and working fine
//            .baseUrl("https://192.168.2.17:443/API/")//127.0.0.1:443 // This is for device
            .baseUrl("https://beststore.receptumelogic.com/")//127.0.0.1:443 // This is for live server

            .addConverterFactory(GsonConverterFactory.create())
            .client(getUnsafeOkHttpClient().build())//this is for sslhandshake exception
//            .client(getClient())
            .build()

        mService = mRetrofit.create(WebService::class.java)


//       val okHttpClient = getOkHttpBuilder()
//            // Other parameters like connectTimeout(15, TimeUnit.SECONDS)
//            .build()
//
//       val gson = GsonBuilder().setLenient().create()
//
//        mRetrofit = Retrofit.Builder()
//            .baseUrl("https://169.254.186.174//API/")
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//        mService = mRetrofit.create(WebService::class.java)


    }


    fun getOkHttpBuilder(): OkHttpClient.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            OkHttpClient().newBuilder()
        } else {
            // Workaround for the error "Caused by: com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException: Could not validate certificate: Certificate expired at".
            getUnsafeOkHttpClient()
        }

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(CONNECTION_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
        httpClient.addInterceptor { chain ->
            val request = chain.request().newBuilder()

            chain.proceed(request.build())
        }
        return httpClient.build()
    }

    fun getService(): WebService {
        return mService
    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder =
        try {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate?>?,
                                                    authType: String?) = Unit

                    override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
                }
            )
            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory,
                trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            builder.addInterceptor(interceptor)

        } catch (e: Exception) {
            throw RuntimeException(e)
        }

}