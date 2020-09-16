package klinka.com.notciasagora

import com.google.gson.Gson
import klinka.com.newsnow.model.SourceResponse
import klinka.com.newsnow.service.EndpointService
import klinka.com.newsnow.ui.viewmodel.NewsViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.mock.Calls

class NewsViewModelTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test(){
        val rawResponse = "{\"status\": \"ok\" ,\"sources\": []}"
        val model = Gson().fromJson(rawResponse, SourceResponse::class.java)
        val response = Response.success(model)

        val serviceMock = Mockito.mock(EndpointService::class.java)
        Mockito.`when`(serviceMock.getSources("categoria", "BR"))
            .thenReturn(Calls.response(response))

        val newsViewModel = NewsViewModel(serviceMock)
        newsViewModel.loadSource("categoria", "BR")
        Mockito.verify(serviceMock).getSources("categoria", "BR")
    }
}