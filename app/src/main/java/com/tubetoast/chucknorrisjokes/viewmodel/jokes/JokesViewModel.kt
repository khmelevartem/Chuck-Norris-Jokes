package com.tubetoast.chucknorrisjokes.viewmodel.jokes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tubetoast.chucknorrisjokes.data.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class JokesViewModel : ViewModel() {


    private val repository : Repository = RepositoryImpl()
    private val disposable = CompositeDisposable()

    private val jokes = MutableLiveData<List<String>>()
    private val appState = MutableLiveData<AppState>()

    fun getJokes() : LiveData<List<String>>{
        return jokes
    }

    fun getState() : LiveData<AppState>{
        return appState
    }

    fun loadJokes(count: Int) {
        disposable.add(
            repository.getJokes(count)
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe (
               { jokes.value = it },
               { appState.value = AppState.Error(it) },
               { appState.value = AppState.Success },
               { appState.value = AppState.Loading }
           )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}