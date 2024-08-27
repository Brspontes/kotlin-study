package org.example.br.com.alura.alugames.modelo

data class Game(
    val titulo: String,
    val capa: String) {
    var descricao = ""

    override fun toString(): String {
        return "Game: $titulo \n " +
                "capa: $capa \n " +
                "descrição: $descricao"
    }
}