package com.tubetoast.chucknorrisjokes.data.network

import android.accounts.NetworkErrorException
import com.tubetoast.chucknorrisjokes.data.RepositoryImpl
import com.tubetoast.chucknorrisjokes.data.network.icndb.ICNDbService
import io.reactivex.Observable
import org.jsoup.Jsoup

class NetworkDataSource : RepositoryImpl.DataSource{

    val api = ICNDbService.API

    override fun getJokes(number: Int): Observable<List<String>> {
        return api.getJokes(number).map {dto ->
            if (dto.type != "success") throw NetworkErrorException("Some error")
            dto.value.map {
                Jsoup.parse( it.joke).text()
            }
        }
    }

}