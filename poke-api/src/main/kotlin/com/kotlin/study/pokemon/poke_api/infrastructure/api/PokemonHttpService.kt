package com.kotlin.study.pokemon.poke_api.infrastructure.api

import com.kotlin.study.pokemon.poke_api.application.dtos.response.PokemonDto
import org.springframework.stereotype.Service
import retrofit2.http.GET
import retrofit2.http.Path

@Service
interface PokemonHttpService {
    @GET("pokemon/{name}")
    suspend fun getPokemonByName(@Path("name") name: String): PokemonDto
}
