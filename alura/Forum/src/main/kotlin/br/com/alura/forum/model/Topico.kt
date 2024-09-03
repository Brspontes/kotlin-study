package br.com.alura.forum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne val curso: Curso,
    @ManyToOne val autor: Usuario,
    @Enumerated(value = EnumType.STRING) val status: StatusTpicoEnum = StatusTpicoEnum.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico") val respostas: List<Resposta> = ArrayList()
) {
    constructor(): this(
        id = null,
        titulo = "",
        mensagem = "",
        curso = Curso(),
        autor = Usuario()
    )
}