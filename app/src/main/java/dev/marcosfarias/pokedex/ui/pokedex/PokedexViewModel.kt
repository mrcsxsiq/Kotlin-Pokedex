package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.model.Pokemon
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {

    val isLoadingData = MutableLiveData(false)
    private val pokedexListData = MutableLiveData<List<Pokemon>>()

    fun getPokedexList(
        offset: Int,
        limit: Int
    ): LiveData<List<Pokemon>> {
        viewModelScope.launch {
            pokedexRepository.loadPokedexList(
                offset,
                limit,
                isLoadingData,
                pokedexListData
            )
        }
        return pokedexListData
    }
}