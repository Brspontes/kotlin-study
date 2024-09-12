package br.com.alura.forum.controllers.dto

import br.com.alura.forum.model.StatusTpicoEnum
import java.io.Serializable
import java.time.LocalDateTime

data class TopicoResponse(
    val id: Long,
    var titulo: String,
    var mensagem: String,
    val status: StatusTpicoEnum,
    val dataCriacal: LocalDateTime,
    val dataAlteracao: LocalDateTime?
): Serializable