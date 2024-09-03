package br.com.alura.forum.services

import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repositories.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {
    fun buscarPorId(id: Long): Optional<Usuario> {
        return usuarioRepository.findById(id)
    }
}
