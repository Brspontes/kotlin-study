import javax.persistence.*

@Entity(name = "jogos")
@Table(name = "jogos")
class JogoEntity(
    val titulo: String = "Título do jogo",
    val capa: String = "Capa do jogo",
    val preco: Double = 0.0,
    val descricao: String? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0
) {
    // Construtor padrão requerido pelo Hibernate
    constructor() : this("Título do jogo", "Capa do jogo", 0.0, null, 0)
}