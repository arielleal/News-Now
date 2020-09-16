package klinka.com.newsnow.service

import klinka.com.newsnow.model.SourceResponse
import retrofit2.Call
import retrofit2.Retrofit

class EndpointService: EndpointServiceInterface {

    override fun getSources(category: String?, country: String?): Call<SourceResponse> {
        val retrofitClient: Retrofit = ServiceConfiguration().getRetrofit()
        val endpoint = retrofitClient.create(NewsService::class.java)
        return endpoint.getSources(country, category)
    }
}