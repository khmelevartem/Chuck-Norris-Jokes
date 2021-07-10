package com.tubetoast.chucknorrisjokes.viewmodel.jokes

import io.reactivex.Observable

interface Repository {

    fun getJokes(count: Int) : Observable<List<String>>

}