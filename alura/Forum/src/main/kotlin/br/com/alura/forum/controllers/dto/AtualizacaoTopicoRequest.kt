package br.com.alura.forum.controllers.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class AtualizacaoTopicoRequest (
    @field:NotEmpty val id: Long,
    @field:NotEmpty @field:Size(min = 3, max = 100) val titulo: String,
    @field:NotEmpty val mensagem: String
)
