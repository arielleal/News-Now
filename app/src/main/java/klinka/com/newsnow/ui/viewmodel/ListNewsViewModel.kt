package klinka.com.newsnow.ui.viewmodel

import androidx.lifecycle.ViewModel
import klinka.com.newsnow.model.ArticleResponse
import klinka.com.newsnow.service.EndPointService
import klinka.com.newsnow.service.NewsService
import klinka.com.newsnow.ui.activity.ListNewsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNewsViewModel(private val listActivity: ListNewsActivity): ViewModel() {

    private val retrofitClient = EndPointService().getRetrofit()

    fun loadEverything(domain: String, pageSize: Int ) {
        val endpoint = retrofitClient.create(NewsService::class.java)
        val everythingCallback = endpoint.getEverything(domain, pageSize)

        everythingCallback.enqueue(object: Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                listActivity.fillArticles(response.body()!!.articles)
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                listActivity.showErrorToastListNews()
            }

        })
    }}