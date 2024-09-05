package br.com.alura.forum.repositories.custom

import br.com.alura.forum.controllers.dto.TopicoPorCategoriaDto

interface TopicoRepositoryCustom {
    fun relatorio(): List<TopicoPorCategoriaDto>
}