package com.kotlin.study.pokemon.poke_api.application.controllers

import com.kotlin.study.pokemon.poke_api.application.dtos.response.PokemonDto
import com.kotlin.study.pokemon.poke_api.domain.services.PokemonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pokemon")
class PokemonController(private val pokemonService: PokemonService) {
    @GetMapping
    suspend fun getPokemon(@RequestParam name: String): PokemonDto = withContext(Dispatchers.IO) {
        pokemonService.getPokemonByName(name)
    }
}