package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.*
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {
    fun getPokedexListIsLoading() : LiveData<Boolean> = pokedexRepository.loadingData

    fun getPokedexList(offset: String,
                       limit: String): LiveData<MutableList<Pokemon>> {
        viewModelScope.launch {
            pokedexRepository.loadPokedexList(offset, limit)
        }

        return pokedexRepository.pokedexListData
    }
}