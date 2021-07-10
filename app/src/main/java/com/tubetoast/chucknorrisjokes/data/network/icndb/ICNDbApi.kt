package com.tubetoast.chucknorrisjokes.data.network.icndb

import com.tubetoast.chucknorrisjokes.data.network.icndb.entities.ResultDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ICNDbApi {

    @GET("/jokes/random/{number}")
    fun getJokes(@Path("number") count: Int) : Observable<ResultDTO>
}