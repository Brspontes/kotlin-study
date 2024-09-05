package br.com.alura.forum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
data class Usuario (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long? = null,
    val nome: String,
    val email: String,
    val password: String,

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)  // JPA carrega todas as roles com o EAGER
    @JoinTable(
        name = "usuario_role",  // Nome da tabela de junção
        joinColumns = [JoinColumn(name = "usuario_id")],  // Coluna na tabela de junção para a entidade Usuario
        inverseJoinColumns = [JoinColumn(name = "role_id")]  // Coluna na tabela de junção para a entidade Role
    )
    val role: List<Role> = mutableListOf()
) {
    constructor(): this(null, "", "", "")

    override fun toString(): String {
        return "Usuario(id=$id, nome='$nome', email='$email', password='$password', role=$role)"
    }
}
