package task.acronyms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import task.acronyms.network.AcronymRepository
import task.acronyms.ui.AcronymViewModel

class MyViewModelFactory constructor(private val repository: AcronymRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AcronymViewModel::class.java)) {
            AcronymViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}