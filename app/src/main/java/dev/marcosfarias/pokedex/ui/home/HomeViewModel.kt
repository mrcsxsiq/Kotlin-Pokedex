package dev.marcosfarias.pokedex.ui.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.marcosfarias.pokedex.R
import dev.marcosfarias.pokedex.model.Menu
import dev.marcosfarias.pokedex.model.News

class HomeViewModel(private val context: Context) : ViewModel() {

    private val listMenu = MutableLiveData<List<Menu>>()
    private val listNews = MutableLiveData<List<News>>()

    fun getListMenu(): LiveData<List<Menu>> {
        listMenu.value = listOf(
            Menu(1, context.resources.getString(R.string.menu_item_1), R.color.lightTeal),
            Menu(1, context.resources.getString(R.string.menu_item_2), R.color.lightRed),
            Menu(1, context.resources.getString(R.string.menu_item_3), R.color.lightBlue),
            Menu(1, context.resources.getString(R.string.menu_item_4), R.color.lightYellow),
            Menu(1, context.resources.getString(R.string.menu_item_5), R.color.lightPurple),
            Menu(1, context.resources.getString(R.string.menu_item_6), R.color.lightBrown)
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
