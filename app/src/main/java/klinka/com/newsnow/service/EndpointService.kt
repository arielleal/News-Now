package klinka.com.newsnow.service

import klinka.com.newsnow.model.ArticleResponse
import klinka.com.newsnow.model.SourceResponse
import retrofit2.Call
import retrofit2.Retrofit

class EndpointService: EndpointServiceInterface {
    private val retrofitClient: Retrofit = ServiceConfiguration().getRetrofit()
    private val endpoint = retrofitClient.create(NewsService::class.java)

    override fun getSources(category: String?, country: String?): Call<SourceResponse> {
        return endpoint.getSources(country, category)
    }

    override fun getEverything(domain: String, pageSize: Int): Call<ArticleResponse> {
        return endpoint.getEverything(domain, pageSize)
    }
}