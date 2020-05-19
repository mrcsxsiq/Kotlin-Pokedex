package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.*
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {

    val isLoadingData = MutableLiveData(false)
    val pokedexListData = MutableLiveData<List<Pokemon>>()

    fun getPokedexList(
        offset: Int,
        limit: Int
    ) {
        viewModelScope.launch {
            pokedexRepository.loadPokedexList(
                offset,
                limit,
                isLoadingData,
                pokedexListData
            )
        }
    }
}