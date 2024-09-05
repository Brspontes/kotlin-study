package br.com.alura.forum.repositories

import br.com.alura.forum.model.Topico
import br.com.alura.forum.repositories.custom.TopicoRepositoryCustom
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository : JpaRepository<Topico, Long>, TopicoRepositoryCustom {
    fun findByCursoNome(nomeCurso: String?): List<Topico>
}