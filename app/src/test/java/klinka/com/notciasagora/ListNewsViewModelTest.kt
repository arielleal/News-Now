package klinka.com.notciasagora

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import klinka.com.newsnow.model.ArticleResponse
import klinka.com.newsnow.service.EndpointService
import klinka.com.newsnow.ui.viewmodel.ListNewsViewModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.notification.Failure
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Response.error
import retrofit2.mock.Calls

class ListNewsViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testloadArticleSuccess() {

        val tlaResponse = "{\"status\":\"ok\" ,\"totalResults\":\"1\" ,\"articles\": []}"
        var model = Gson().fromJson(tlaResponse, ArticleResponse::class.java)
        val response = Response.success(model)

        val serviceMock = Mockito.mock(EndpointService::class.java)
        Mockito.`when`(serviceMock.getEverything("globo.com", 20))
            .thenReturn(Calls.response(response))

        val listNewsViewModel = ListNewsViewModel(serviceMock)
        listNewsViewModel.loadEverything("globo.com", 20)
        Mockito.verify(serviceMock).getEverything("globo.com", 20)
    }

    @Test
    fun testloadArticleFailure() {

        val serviceMock = Mockito.mock(EndpointService::class.java)
        Mockito.`when`(serviceMock.getEverything("globo.com", 20))
            .thenReturn(Calls.failure(Exception("error")))

        val listNewsViewModel = ListNewsViewModel(serviceMock)
        listNewsViewModel.loadEverything("globo.com", 20)
        Mockito.verify(serviceMock).getEverything("globo.com", 20)
    }
}