package com.tubetoast.chucknorrisjokes.viewmodel.jokes

sealed class AppState{
    data class Error(val throwable: Throwable) : AppState()
    object Success : AppState()
    object Loading : AppState()
}
