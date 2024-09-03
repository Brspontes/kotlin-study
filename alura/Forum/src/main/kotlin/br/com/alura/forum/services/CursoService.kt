package br.com.alura.forum.services

import br.com.alura.forum.model.Curso
import br.com.alura.forum.repositories.CursoRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CursoService(private val cursoRepository: CursoRepository) {
    fun buscarPorId(id: Long): Optional<Curso> {
        return cursoRepository.findById(id)
    }
}
