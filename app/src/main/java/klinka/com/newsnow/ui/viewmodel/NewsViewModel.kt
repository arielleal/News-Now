package klinka.com.newsnow.ui.viewmodel
import androidx.lifecycle.ViewModel
import klinka.com.newsnow.model.SourceResponse
import klinka.com.newsnow.service.EndPointService
import klinka.com.newsnow.service.NewsService
import klinka.com.newsnow.ui.activity.NewsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(private val newsActivity: NewsActivity) : ViewModel() {

    private val retrofitClient = EndPointService().getRetrofit()

     fun loadSource(country: String?, category: String?){
        val endpoint = retrofitClient.create(NewsService::class.java)
        val sourcesCallback = endpoint.getSources(country, category)

        sourcesCallback.enqueue(object : Callback<SourceResponse> {
            override fun onResponse(
                call: Call<SourceResponse>,
                response: Response<SourceResponse>
            ) {
                newsActivity.update(response.body()!!.sources)
            }

            override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                newsActivity.showErrorToastNews()
            }

        })
    }


}