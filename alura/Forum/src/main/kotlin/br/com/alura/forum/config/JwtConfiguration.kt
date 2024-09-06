package br.com.alura.forum.config

import br.com.alura.forum.services.UsuarioService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*


@Component
class JwtConfiguration(private val usuarioService: UsuarioService) {
    private val expiration: Long = 60000

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String? {
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

        return Jwts.builder()
            .subject(username)
            .claim("role",authorities)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
            true
        } catch (e: IllegalArgumentException) {
            println(e)
            false
        }
    }

    fun getAuthentication(jwt: String?): UsernamePasswordAuthenticationToken {
        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
        val username = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(jwt)
            .payload
            .subject

        val user = usuarioService.loadUserByUsername(username.toString())
        println(username.toString())
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}