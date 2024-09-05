package br.com.alura.forum.repositories

import br.com.alura.forum.model.Curso
import org.springframework.data.repository.CrudRepository

interface CursoRepository : CrudRepository<Curso, Long> {
}