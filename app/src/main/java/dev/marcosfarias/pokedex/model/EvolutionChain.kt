package dev.marcosfarias.pokedex.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import dev.marcosfarias.pokedex.model.commons.Converters
import me.sargunvohra.lib.pokekotlin.model.ChainLink
import me.sargunvohra.lib.pokekotlin.model.NamedApiResource

@Entity
@TypeConverters(Converters::class)
class EvolutionChain(
    @PrimaryKey
    @NonNull
    val id: Int,
    val babyTriggerItem: NamedApiResource?,
    val chain: ChainLink
)