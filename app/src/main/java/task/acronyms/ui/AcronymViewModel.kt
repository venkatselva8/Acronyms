package task.acronyms.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import task.acronyms.model.AcronymResponse
import task.acronyms.network.AcronymRepository

class AcronymViewModel constructor(private val repository: AcronymRepository) : ViewModel() {

    val acronymList = MutableLiveData<List<AcronymResponse>>()
    val errorApi = MutableLiveData<Exception>()
    val errorApiMessage = MutableLiveData<String>()

    fun getAcronymList(shortForm: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getListOfAcronym(shortForm)
                if (response.isSuccessful && response.body() != null) {
                    acronymList.postValue(response.body())
                } else {
                    errorApiMessage.postValue("Something went wrong. Try again later")
                }
            } catch (e: Exception) {
                errorApi.postValue(e)
            }
        }
    }

    fun isValidText(enteredText: String): Boolean = enteredText.isNotEmpty()
}