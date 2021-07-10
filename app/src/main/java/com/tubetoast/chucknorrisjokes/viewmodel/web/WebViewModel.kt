package com.tubetoast.chucknorrisjokes.viewmodel.web

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewModel : ViewModel() {

    private val url = MutableLiveData<String>()
    init {
        url.value = BASE_URL
    }

    fun getUrl(): LiveData<String> {
        return url
    }

    fun setUrl(newUrl: String?) {
        url.value = newUrl ?: BASE_URL
    }

    companion object {
        const val BASE_URL: String = "https://icndb.com/api"
    }

}
