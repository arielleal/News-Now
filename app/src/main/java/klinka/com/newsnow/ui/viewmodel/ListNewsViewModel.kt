package klinka.com.newsnow.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import klinka.com.newsnow.model.Article
import klinka.com.newsnow.model.ArticleResponse
import klinka.com.newsnow.service.EndpointService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListNewsViewModel(private val endpointService: EndpointService = EndpointService()) : ViewModel() {

    val category = MutableLiveData<List<Article>>()
    val errorMenssage = MutableLiveData<String>()

    fun loadEverything(domain: String, pageSize: Int ) {
        val everythingCallback = endpointService.getEverything(domain, pageSize)

        everythingCallback.enqueue(object: Callback<ArticleResponse> {
            override fun onResponse(
                call: Call<ArticleResponse>,
                response: Response<ArticleResponse>
            ) {
                category.postValue(response.body()!!.articles)
            }

            override fun onFailure(call: Call<ArticleResponse>, t: Throwable) {
                errorMenssage.postValue("This content cloud not be loaded" + t.message)
            }
        })
    }}