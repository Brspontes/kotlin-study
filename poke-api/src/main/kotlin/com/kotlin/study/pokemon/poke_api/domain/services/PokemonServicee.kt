package com.kotlin.study.pokemon.poke_api.domain.services

import com.kotlin.study.pokemon.poke_api.application.dtos.response.PokemonDto
import com.kotlin.study.pokemon.poke_api.domain.contracts.PokemonService
import com.kotlin.study.pokemon.poke_api.infrastructure.api.PokemonHttpService
import org.springframework.stereotype.Service

@Service
class PokemonService(private val pokemonHttpService: PokemonHttpService) : PokemonService {
    override suspend fun getPokemonByName(name: String): PokemonDto {
        return pokemonHttpService.getPokemonByName(name)
    }
}