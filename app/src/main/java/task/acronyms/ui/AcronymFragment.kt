package task.acronyms.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import task.acronyms.MyViewModelFactory
import task.acronyms.R
import task.acronyms.databinding.AcronymFragmentBinding
import task.acronyms.network.AcronymRepository
import task.acronyms.network.RetrofitService


class AcronymFragment : Fragment() {

    companion object {
        fun newInstance() = AcronymFragment()
    }

    private lateinit var binding: AcronymFragmentBinding
    private lateinit var viewModel: AcronymViewModel
    private val retrofitService = RetrofitService.getInstance()
    val acronymLfAdapter = AcronymLfAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.acronym_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(AcronymRepository(retrofitService))).get(
                AcronymViewModel::class.java
            )

        binding.recyclerview.adapter = acronymLfAdapter

        viewModel.acronymList.observe(this, Observer {
            Log.d("AcronymList", "$it")
            if (it.isNotEmpty()) {
                if (it[0].lfs.isNotEmpty()) {
                    acronymLfAdapter.setAcronymLfList(it[0].lfs)
                    return@Observer
                }
            }
            acronymLfAdapter.clearData()
            showToast("No data found")
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.d("AcronymList", "error $it")
        })

        binding.imbSearch.setOnClickListener {
            onSearchButtonClick()
        }

        binding.tvInput.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchButtonClick()
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun onSearchButtonClick() {
        val enteredText = binding.tvInput.text.toString()
        if (viewModel.isValidText(enteredText)) {
            this.hideKeyboard()
            viewModel.getAcronymList(enteredText)
        } else {
            showToast("Please enter valid text")
        }
    }

    //Utils
    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}