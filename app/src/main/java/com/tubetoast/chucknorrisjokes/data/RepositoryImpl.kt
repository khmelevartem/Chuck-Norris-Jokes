package com.tubetoast.chucknorrisjokes.data

import com.tubetoast.chucknorrisjokes.data.network.NetworkDataSource
import com.tubetoast.chucknorrisjokes.viewmodel.jokes.Repository
import io.reactivex.Observable

class RepositoryImpl : Repository {

    private val dataSource: DataSource = NetworkDataSource()

    override fun getJokes(count: Int): Observable<List<String>> {
        return dataSource.getJokes(count)
    }

    interface DataSource{
        fun getJokes(count: Int): Observable<List<String>>
    }
}