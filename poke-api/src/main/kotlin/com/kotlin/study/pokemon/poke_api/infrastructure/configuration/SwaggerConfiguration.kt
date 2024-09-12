package com.kotlin.study.pokemon.poke_api.infrastructure.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {
    @Bean
    fun swaggerConfig(): OpenAPI = OpenAPI()
        .info(Info()
            .description("API Estudo Kotlin")
            .title("API Pokemon")
            .version("1.0"))
}