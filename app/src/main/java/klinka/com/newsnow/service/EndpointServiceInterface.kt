package klinka.com.newsnow.service

import klinka.com.newsnow.model.SourceResponse
import retrofit2.Call

interface EndpointServiceInterface {

    fun getSources(category: String?, country: String?): Call<SourceResponse>
}