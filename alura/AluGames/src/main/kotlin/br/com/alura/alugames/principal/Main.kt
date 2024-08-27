package org.example.br.com.alura.alugames.principal

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.servicos.Api
import com.google.gson.Gson
import org.example.br.com.alura.alugames.modelo.Game
import org.example.br.com.alura.alugames.modelo.InfoGame
import java.util.*

fun main() {
    val leitura = Scanner(System.`in`)
    val gamer = Gamer.criarGamer(leitura)
    do {
        println("Digite um id de jogo: ")
        val busca = leitura.nextLine()

        val resultApi = Api().getData(busca)

        val gson = Gson()
        var myGame: Game? = null
        val result = runCatching {
            val infoGame = gson.fromJson(resultApi, InfoGame::class.java)
            myGame = Game(infoGame.info.title, infoGame.info.thumb)
        }

        result.onFailure {
            println("Jogo não encontrado")
        }

        result.onSuccess {
            println("Inserir descrição? S/N")
            val opcao = leitura.nextLine()
            if (opcao.equals("s", true)) {
                println("Digite a descrição")
                val descricao = leitura.nextLine()
                myGame?.descricao = descricao
            } else {
                myGame?.descricao = myGame?.titulo!!
            }
            gamer.jogosBuscados.add(myGame)
        }
        println("Deseja consultar novo jogo? S/N")
        val novoJogo = leitura.nextLine()
    } while (novoJogo.equals("s", true))

    println("Jogos buscados \n ${gamer.jogosBuscados} \n")
    println("\n \n Jogos ordenados por título \n ${gamer.jogosBuscados.sortBy { it?.titulo }} \n")
}

//fun main () {
//    val gamer1 = Gamer("Brian", "brian.robert16@hotmail.com")
//    val gamer2 = Gamer("Brian", "brian.robert16@hotmail.com", "13/08/1995", "brspontes")
//
//    println(gamer1)
//    println(gamer2)
//
//    gamer1.let {
//        it.dataNascimento = "20/09/2000"
//        it.usuario = "brspontes2"
//    }
//}