package br.com.alura.forum.repositories

import br.com.alura.forum.model.Usuario
import org.springframework.data.repository.CrudRepository

interface UsuarioRepository : CrudRepository<Usuario, Long> {
    fun findByEmail(username: String): Usuario?
}