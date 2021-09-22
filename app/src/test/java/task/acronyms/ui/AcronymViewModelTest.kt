package task.acronyms.ui

import android.os.Build
import androidx.lifecycle.MutableLiveData
import androidx.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import retrofit2.Response
import task.acronyms.model.AcronymResponse
import task.acronyms.network.AcronymRepository
import task.acronyms.network.RetrofitService
import java.io.IOException

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class AcronymViewModelTest {

    lateinit var acronymViewModel: AcronymViewModel
    lateinit var acronymRepository: AcronymRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        acronymViewModel = AcronymViewModel(AcronymRepository(RetrofitService.getInstance()))
        acronymRepository = AcronymRepository(RetrofitService.getInstance())
    }

    @Test
    fun isValidText() {
        Assert.assertEquals(acronymViewModel.isValidText("abc"), true)
        Assert.assertEquals(acronymViewModel.isValidText("nasa"), true)
        Assert.assertEquals(acronymViewModel.isValidText(""), false)
    }

    @Test
    fun getAcronymnByApiCall() {
        try {
            val rawResponse: Response<List<AcronymResponse>> =
                acronymRepository.getListOfAcronym("nasa").execute()
            val acronymResponse = rawResponse.body() as List<AcronymResponse>
            assertTrue(rawResponse.isSuccessful)
            assertTrue(acronymResponse.isNotEmpty())
            assertEquals(acronymResponse[0].lfs[0].lf, "National Aeronautics and Space Administration")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}