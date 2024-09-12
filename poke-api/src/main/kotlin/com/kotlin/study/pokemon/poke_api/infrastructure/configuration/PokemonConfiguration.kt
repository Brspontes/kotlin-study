package com.kotlin.study.pokemon.poke_api.infrastructure.configuration

import com.kotlin.study.pokemon.poke_api.infrastructure.api.PokemonHttpService
import okhttp3.OkHttpClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Configuration
class PokemonConfiguration {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    private fun buildClient() = OkHttpClient.Builder().build()

    private fun buildRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(buildClient())
        .build()

    @Bean
    fun carHttpService(): PokemonHttpService = buildRetrofit().create(PokemonHttpService::class.java)

}