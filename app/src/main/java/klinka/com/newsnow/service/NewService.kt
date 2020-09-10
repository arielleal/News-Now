package klinka.com.newsnow.service

import klinka.com.newsnow.model.ArticleResponse
import klinka.com.newsnow.model.SourceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/sources")
    fun getSources(@Query("country") country: String?,
                   @Query("category") category: String?): Call<SourceResponse>

    @GET("/v2/everything")
    fun getEverything(@Query("domains") domains: String?,
                      @Query("pageSize") pageSize: Int ): Call<ArticleResponse>
}
