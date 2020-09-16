package klinka.com.newsnow.service

import klinka.com.newsnow.API_KEY
import klinka.com.newsnow.API_KEY_HEADER_NAME
import klinka.com.newsnow.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.HashMap

open class ServiceConfiguration {

    private var retrofit: Retrofit? = null
    private var httpClient: OkHttpClient? = null

    fun getRetrofit(): Retrofit {
        return retrofit?: let{
            retrofit = Retrofit.Builder()
                .client(getHttpClient())
                .baseUrl(getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit as Retrofit

        }
    }
    protected open fun getBaseURL() = BASE_URL

    private fun getHttpClient(): OkHttpClient {
        return httpClient ?: let {
            httpClient = getBuilder().build()
            return httpClient as OkHttpClient
        }
    }

    protected open fun getBuilder(): OkHttpClient.Builder {

        val builder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        builder.addInterceptor(::addHeaders)

        return builder
    }

    //add the API_KEY header
    private fun addHeaders(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().addHeader(API_KEY_HEADER_NAME, API_KEY).build()
        return chain.proceed(request)
    }
}