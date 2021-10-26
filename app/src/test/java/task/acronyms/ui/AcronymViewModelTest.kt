package task.acronyms.ui

import android.os.Build
import androidx.test.runner.AndroidJUnit4
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import task.acronyms.network.AcronymRepository
import task.acronyms.network.RetrofitService

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(AndroidJUnit4::class)
class AcronymViewModelTest {

    lateinit var acronymViewModel: AcronymViewModel
    lateinit var acronymRepository: AcronymRepository

    @Before
    fun setUp() {
        acronymViewModel = AcronymViewModel(AcronymRepository(RetrofitService.getInstance()))
        acronymRepository = AcronymRepository(RetrofitService.getInstance())
    }

    @Test
    fun isValidText() {
        Assert.assertEquals(acronymViewModel.isValidText("abc"), true)
        Assert.assertEquals(acronymViewModel.isValidText("nasa"), true)
        Assert.assertEquals(acronymViewModel.isValidText(""), false)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAcronymByApiCall() = runBlocking {
        val response = acronymRepository.getListOfAcronym("nasa")
        val listOfAcronym = response.body()
        assertTrue(response.isSuccessful)
        assertTrue(listOfAcronym!!.isNotEmpty())
        assertEquals(
            listOfAcronym.firstOrNull()!!.lfs[0].lf,
            "National Aeronautics and Space Administration"
        )
    }
}