package com.kotlin.study.pokemon.poke_api.domain.contracts

import com.kotlin.study.pokemon.poke_api.application.dtos.response.PokemonDto

interface PokemonService {
    suspend fun getPokemonByName(name: String): PokemonDto
}