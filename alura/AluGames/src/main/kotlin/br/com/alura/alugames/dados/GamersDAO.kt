package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Gamer
import br.com.alura.alugames.modelo.GamerEntity
import javax.persistence.EntityManager

class GamersDAO(manager: EntityManager): GenericDAO<GamerEntity>(manager, GamerEntity::class.java) {
}