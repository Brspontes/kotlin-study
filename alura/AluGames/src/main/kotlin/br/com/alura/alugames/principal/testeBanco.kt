package br.com.alura.alugames.principal

import JogoEntity
import br.com.alura.alugames.dados.Banco
import br.com.alura.alugames.dados.JogosDAO
import br.com.alura.alugames.modelo.Jogo

fun main() {
    val manager = Banco.getEntityManager()
    val jogosDAO = JogosDAO(manager)

    val jogo = Jogo("Dandara", "https://cdn.cloudflare.steamstatic.com/steam/apps/612390/header.jpg?t=1674055293",
        9.99, "Um jogo de plataforma e ação com elementos de metroidvania, onde você controla a heroína Dandara em sua luta para libertar um mundo repleto de opressão e tirania.")

    jogosDAO.addEntity(JogoEntity(jogo.titulo, jogo.capa, jogo.preco, jogo.descricao))

    val jogoRecuperado = jogosDAO.recuperarPorId(3)
    jogosDAO.remover(6)
    val listaJogos: List<JogoEntity> = jogosDAO.getListEntity()

    println(listaJogos)
    manager.close()
}