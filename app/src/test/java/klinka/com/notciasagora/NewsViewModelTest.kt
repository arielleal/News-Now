package klinka.com.notciasagora

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import klinka.com.newsnow.model.SourceResponse
import klinka.com.newsnow.service.EndpointService
import klinka.com.newsnow.ui.viewmodel.NewsViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.mock.Calls

class NewsViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testLoadSouce(){
        val tlsResponse = "{\"status\": \"ok\" ,\"sources\": []}"
        val model = Gson().fromJson(tlsResponse, SourceResponse::class.java)
        val response = Response.success(model)

        val serviceMock = Mockito.mock(EndpointService::class.java)
        Mockito.`when`(serviceMock.getSources("categoria", "br"))
            .thenReturn(Calls.response(response))

        val newsViewModel = NewsViewModel(serviceMock)
        newsViewModel.loadSource("br", "categoria")
        Mockito.verify(serviceMock).getSources("categoria", "br")
    }

    @Test
    fun testloadSourceFailure() {

        val serviceMock = Mockito.mock(EndpointService::class.java)
        Mockito.`when`(serviceMock.getSources("category", "br"))
            .thenReturn(Calls.failure(Exception("error")))

        val NewsViewModel = NewsViewModel(serviceMock)
        NewsViewModel.loadSource("br", "category")
        Mockito.verify(serviceMock).getSources("category", "br")
    }
}