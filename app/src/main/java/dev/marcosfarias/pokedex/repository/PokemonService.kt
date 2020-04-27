package dev.marcosfarias.pokedex.repository

import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.model.PokemonResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon")
    fun getNextList(@Query("offset") offset: String, @Query("limit") limit: String) : Observable<PokemonResult>

    @GET("pokemon/{id}")
    fun getById(@Path("id") id: String): Observable<Pokemon>
}
