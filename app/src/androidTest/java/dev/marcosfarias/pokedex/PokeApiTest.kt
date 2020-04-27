package dev.marcosfarias.pokedex

import android.net.Uri
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.GsonBuilder
import dev.marcosfarias.pokedex.model.Pokemon
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@RunWith(AndroidJUnit4::class)
class PokeApiTest {

    @Test
    fun loadPokemonListTest() {
        val apiTest = ApiTest()
        apiTest.loadPokemons()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pokemonsId ->
                Log.d("ApiTest", "Pokemon Count: ${pokemonsId?.size.toString()}")
            }, { error ->
                error.printStackTrace()
                assert(false)
            }, {

            }, {
                assert(true)
            })
    }

    @Test
    fun loadPokemonFirstTest() {
        val apiTest = ApiTest()
        apiTest.loadPokemonFirstList()
            .subscribeOn(Schedulers.trampoline())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pokemonList ->
                Log.d("ApiTest", "Pokemon Count: ${pokemonList.size}")
                assert(true)
            },{ error ->
                error.printStackTrace()
                assert(false)
            })
    }

    @Test
    fun loadPokemonFullTest() {
        val count = 0

        val apiTest = ApiTest()
        apiTest.loadNextList(count.toString(), count.toString())
            .subscribeOn(Schedulers.trampoline())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ pokemon ->
                Log.d("ApiTest", "Pokemon Id: ${pokemon.id} | Name: ${pokemon.name}")
            }, { error ->
                error.printStackTrace()
                assert(false)
            }, {
                assert(true)
            })
    }
}

class ApiTest {<PokemonApiDef>
    private val service: PokemonApiDef

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

        service = retrofit.create(PokemonApiDef::class.java)
    }

    fun loadPokemonsCount(): Observable<Int>? {
        return service.getList()
            .flatMap { pokemonsResults ->
                Observable.just(pokemonsResults.count)
            }
    }

    fun loadPokemons(): Observable<List<Int?>> {
        return service.getList()
            .flatMap { pokemonResults -> Observable.just(pokemonResults.results) }
            .map { pokemonNames ->
                pokemonNames.map { pokemonName ->
                    Uri.parse(pokemonName.url).lastPathSegment?.toInt()
                }
            }

    }

    fun loadPokemonFirstList(): Single<MutableList<Pokemon>> {
        return service.getList()
            .flatMap { pokemonResults ->
                Observable.fromIterable(pokemonResults.results)
            }
            .flatMap { pokemonName ->
                service.getById(Uri.parse(pokemonName.url).lastPathSegment.toString())
            }.map { pokemon ->
                Pokemon(
                    pokemon.id,
                    pokemon.name,
                    pokemon.baseExperience,
                    pokemon.height,
                    pokemon.isDefault,
                    pokemon.order,
                    pokemon.weight,
                    pokemon.species,
                    pokemon.abilities,
                    pokemon.forms,
                    pokemon.gameIndices,
                    pokemon.heldItems,
                    pokemon.moves,
                    pokemon.stats,
                    pokemon.types,
                    pokemon.sprites
                )
            }.toList()
    }

    fun loadNextList(
        offset: String,
        limit: String) : Observable<Pokemon> {
        return service.getNextList(offset, limit)
            .flatMap { pokemonResults ->
                Observable.fromIterable(pokemonResults.results)
            }.flatMap { pokemonName ->
                service.getById(Uri.parse(pokemonName.url).lastPathSegment.toString())
            }.map { pokemon ->
                Pokemon(
                    pokemon.id,
                    pokemon.name,
                    pokemon.baseExperience,
                    pokemon.height,
                    pokemon.isDefault,
                    pokemon.order,
                    pokemon.weight,
                    pokemon.species,
                    pokemon.abilities,
                    pokemon.forms,
                    pokemon.gameIndices,
                    pokemon.heldItems,
                    pokemon.moves,
                    pokemon.stats,
                    pokemon.types,
                    pokemon.sprites
                )
            }
    }
}

interface PokemonApiDef {
    @GET("pokemon/")
    fun getList(): Observable<PokemonResult>

    @GET("pokemon")
    fun getNextList(@Query("{offset}") offset: String, @Query("{limit}") limit: String) : Observable<PokemonResult>

    @GET("pokemon/{id}")
    fun getById(@Path("id") id: String): Observable<Pokemon>
}

data class PokemonResult(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<PokemonName>
)

data class PokemonName (
    val name: String,
    val url: String
)