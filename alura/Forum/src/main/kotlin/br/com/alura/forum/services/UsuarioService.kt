package br.com.alura.forum.services

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repositories.UsuarioRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) : UserDetailsService {
    fun buscarPorId(id: Long): Optional<Usuario> {
        return usuarioRepository.findById(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        println("loadUserByUsername <<<<<<<<<<<<<<<")
        val usuario = usuarioRepository.findByEmail(username!!) ?: throw RuntimeException("Error loadUserByUsername")
        println(">>>>> AQUI ${usuario.role}")
        return UserDetail(usuario)
    }
}
