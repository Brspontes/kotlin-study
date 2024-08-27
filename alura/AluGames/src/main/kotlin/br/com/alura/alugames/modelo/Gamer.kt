package br.com.alura.alugames.modelo

import org.example.br.com.alura.alugames.modelo.Game
import java.util.Scanner
import kotlin.random.Random

data class Gamer(var nome: String, var email: String) {
    var dataNascimento: String? = null

    var usuario: String? = null
        set(value) {
            field = value
            if (this.idInterno.isNullOrBlank()) {
                criarIdInterno()
            }
        }

    var idInterno: String? = null
        private set

    val jogosBuscados = mutableListOf<Game?>()

    constructor(nome: String, email: String, dataNascimento: String, usuario: String) :
            this(nome, email) {
                this.dataNascimento = dataNascimento
                this.usuario = usuario

                criarIdInterno()
            }

    override fun toString(): String {
        return "Gamer(nome='$nome', email='$email', dataNascimento=$dataNascimento, usuario=$usuario, idInterno=$idInterno)"
    }

    fun criarIdInterno() {
        val numero = Random.nextInt(100000)
        val tag = String.format("%04d", numero)

        this.idInterno = "$usuario#$tag"
    }

    init {
        if (nome.isBlank()) {
            throw IllegalArgumentException("Nome está em branco")
        }
    }

    companion object {
        fun criarGamer(leitura: Scanner) : Gamer {
            println("Nome do Gamer")
            val nome = leitura.nextLine()
            println("Email do Gamer")
            val email = leitura.nextLine()
            println("Deseja completar o cadastro?")
            val opcao = leitura.nextLine()

            if (opcao.equals("s", true)) {
                println("Data nascimento:")
                val dataNascimento = leitura.nextLine()

                println("Nome usuário:")
                val usuario = leitura.nextLine()
                return Gamer(nome, email, dataNascimento, usuario)
            }
            return Gamer(nome, email)
        }
    }
}