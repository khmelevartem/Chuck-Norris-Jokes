package com.tubetoast.chucknorrisjokes.data.network.icndb.entities

data class ResultDTO (
    val type: String,
    val value: List<Joke>
){
    data class Joke(
        val id: Int,
        val joke: String
    )
}