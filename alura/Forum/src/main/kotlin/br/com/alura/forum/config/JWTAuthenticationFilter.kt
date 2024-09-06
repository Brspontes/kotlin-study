package br.com.alura.forum.config

import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder

class JWTAuthenticationFilter(private val jwtUtil: JwtConfiguration) : Filter {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filterChain: FilterChain?) {
        val httpRequest = request as HttpServletRequest
        val token = request.getHeader("Authorization")

        val jwt = getTokenDetail(token)
        if (jwtUtil.isValid(jwt)) {
            val authentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain?.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        return token?.removePrefix("Bearer ")?.takeIf { it.isNotBlank() }
    }
}
