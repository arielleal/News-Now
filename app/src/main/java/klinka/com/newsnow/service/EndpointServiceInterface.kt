package klinka.com.newsnow.service

import klinka.com.newsnow.model.ArticleResponse
import klinka.com.newsnow.model.SourceResponse
import retrofit2.Call

interface EndpointServiceInterface {

    fun getSources(category: String?, country: String?): Call<SourceResponse>
    fun getEverything(domains: String, pageSize: Int): Call<ArticleResponse>
}