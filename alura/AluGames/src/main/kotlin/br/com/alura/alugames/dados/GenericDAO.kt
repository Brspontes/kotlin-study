package br.com.alura.alugames.dados

import br.com.alura.alugames.modelo.Jogo
import javax.persistence.EntityManager

abstract class GenericDAO<TEntity>(protected val manager: EntityManager, protected val entityType: Class<TEntity>) {
    open fun getListEntity(): List<TEntity> {
        val query = manager.createQuery("from ${entityType.simpleName}", entityType)
        return query.resultList
    }

    open fun addEntity(entity: TEntity) {
        manager.transaction.begin()
        manager.persist(entity)
        manager.transaction.commit()
    }

    open fun recuperarPorId(id: Int): TEntity {
        val query = manager.createQuery("from ${entityType.simpleName} where id=:id", entityType)
        query.setParameter("id", id)
        return query.singleResult
    }

    open fun remover(id: Int) {
        val result = recuperarPorId(id)
        manager.transaction.begin()
        manager.remove(result)
        manager.transaction.commit()
    }
}