package dev.marcosfarias.pokedex.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

@Entity
data class EvolutionChain (
    @PrimaryKey
    @NonNull
    @SerializedName("id") override val id: Int,
    @SerializedName("baby_trigger_item") override val babyTriggerItem: NamedApiResource?,
    @SerializedName("chain") override val chain: ChainLink
) : EvolutionChainApiInterface

interface EvolutionChainApiInterface {
    val id: Int
    val babyTriggerItem: NamedApiResource?
    val chain: ChainLink
}