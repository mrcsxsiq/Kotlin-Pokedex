package dev.marcosfarias.pokedex.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.News

class HomeViewModel : ViewModel() {

    private val listMenu = MutableLiveData<List<Menu>>()
    private val listNews = MutableLiveData<List<News>>()

    fun getListMenu(): LiveData<List<Menu>> {
        listMenu.value = listOf(
            Menu(id = 1, name = R.string.menu_item_1, color = R.color.lightTeal),
            Menu(id = 1, name = R.string.menu_item_2, color = R.color.lightRed),
            Menu(id = 1, name = R.string.menu_item_3, color = R.color.lightBlue),
            Menu(id = 1, name = R.string.menu_item_4, color = R.color.lightYellow),
            Menu(id = 1, name = R.string.menu_item_5, color = R.color.lightPurple),
            Menu(id = 1, name = R.string.menu_item_6, color = R.color.lightBrown)
        )
        return listMenu
    }

    fun getListNews(): LiveData<List<News>> {
        listNews.value = listOf(
            News(),
            News(),
            News(),
            News(),
            News(),
            News(),
            News(),
            News()
        )
        return listNews
    }
}
