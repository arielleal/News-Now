package klinka.com.newsnow.ui.viewmodel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import klinka.com.newsnow.model.Source
import klinka.com.newsnow.model.SourceResponse
import klinka.com.newsnow.service.EndpointService
import klinka.com.newsnow.service.ServiceConfiguration
import klinka.com.newsnow.service.NewsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class NewsViewModel(private val endpointService: EndpointService = EndpointService()) : ViewModel() {

    var sources = MutableLiveData<List<Source>>()
    var errorMessage = MutableLiveData<String>()

     fun loadSource(country: String?, category: String?){
        val sourcesCallback = endpointService.getSources(category, country)

        sourcesCallback.enqueue(object : Callback<SourceResponse> {
            override fun onResponse(
                call: Call<SourceResponse>,
                response: Response<SourceResponse>
            ) {
                sources.postValue(response.body()!!.sources)
            }

            override fun onFailure(call: Call<SourceResponse>, t: Throwable) {
                errorMessage.postValue("This content cloud not be loaded" + t.message)
                //newsActivity.showErrorToastNews()

            }
        })
    }


}