package dev.marcosfarias.pokedex.model.commons

import dev.marcosfarias.pokedex.model.EvolutionChain
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.model.Species

fun me.sargunvohra.lib.pokekotlin.model.Pokemon.toModel() = Pokemon(
    id = id,
    name = name,
    baseExperience = baseExperience,
    height = height,
    isDefault = isDefault,
    order = order,
    weight = weight,
    species = species,
    abilities = abilities,
    forms = forms,
    gameIndices = gameIndices,
    heldItems = heldItems,
    moves = moves,
    stats = stats,
    types = types,
    sprites = sprites
)

fun me.sargunvohra.lib.pokekotlin.model.PokemonSpecies.toModel() = Species(
    id = id,
    name = name,
    order = order,
    genderRate = genderRate,
    captureRate = captureRate,
    baseHappiness = baseHappiness,
    isBaby = isBaby,
    hatchCounter = hatchCounter,
    hasGenderDifferences = hasGenderDifferences,
    formsSwitchable = formsSwitchable,
    growthRate = growthRate,
    pokedexNumbers = pokedexNumbers,
    eggGroups = eggGroups,
    color = color,
    shape = shape,
    evolvesFromSpecies = evolvesFromSpecies,
    evolutionChain = evolutionChain,
    habitat = habitat,
    generation = generation,
    names = names,
    palParkEncounters = palParkEncounters,
    formDescriptions = formDescriptions,
    genera = genera,
    varieties = varieties,
    flavorTextEntries = flavorTextEntries
)

fun me.sargunvohra.lib.pokekotlin.model.EvolutionChain.toModel() = EvolutionChain(
    id = id,
    babyTriggerItem = babyTriggerItem,
    chain = chain
)