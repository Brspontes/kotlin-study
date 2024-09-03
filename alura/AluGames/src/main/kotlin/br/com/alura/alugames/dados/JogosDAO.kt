package br.com.alura.alugames.dados

import JogoEntity
import br.com.alura.alugames.modelo.Jogo
import javax.persistence.EntityManager


class JogosDAO(manager: EntityManager): GenericDAO<JogoEntity>(manager, JogoEntity::class.java) {

}