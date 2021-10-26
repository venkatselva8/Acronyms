package task.acronyms.ui

import task.acronyms.model.AcronymResponse
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import task.acronyms.network.AcronymRepository

class AcronymViewModel constructor(private val repository: AcronymRepository) : ViewModel() {

    val acronymList = MutableLiveData<List<AcronymResponse>>()
    val errorApi = MutableLiveData<Throwable>()

    fun getAcronymList(shortForm: String) {
        val response = repository.getListOfAcronym(shortForm)
        response.enqueue(object : Callback<List<AcronymResponse>> {
            override fun onResponse(
                call: Call<List<AcronymResponse>>,
                response: Response<List<AcronymResponse>>
            ) {
                acronymList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<AcronymResponse>>, t: Throwable) {
                errorApi.postValue(t)
            }
        })
    }

    fun isValidText(enteredText: String):Boolean = enteredText.isNotEmpty()
}