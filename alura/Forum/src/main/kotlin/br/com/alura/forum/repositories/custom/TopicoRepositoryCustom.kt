package br.com.alura.forum.repositories.custom

import br.com.alura.forum.controllers.dto.TopicoPorCategoriaDto
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.persistence.criteria.CriteriaBuilder
import jakarta.persistence.criteria.CriteriaQuery
import jakarta.persistence.criteria.Join
import jakarta.persistence.criteria.Root
import org.springframework.stereotype.Repository

@Repository
class TopicoRepositoryCustomImpl  : TopicoRepositoryCustom {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun relatorio(): List<TopicoPorCategoriaDto> {
// Obtém o CriteriaBuilder a partir do EntityManager. O CriteriaBuilder é utilizado para criar e configurar consultas usando a API Criteria.
        val criteriaBuilder: CriteriaBuilder = entityManager.criteriaBuilder

// Cria uma nova instância de CriteriaQuery, especificando que o resultado será do tipo TopicoPorCategoriaDto.
// O CriteriaQuery define a estrutura da consulta, incluindo o tipo de resultado esperado.
        val criteriaQuery: CriteriaQuery<TopicoPorCategoriaDto> = criteriaBuilder.createQuery(TopicoPorCategoriaDto::class.java)

// Define o "root" da consulta, que representa a entidade principal da consulta. Neste caso, o root é da entidade Topico.
// O root é o ponto de partida da consulta e permite a construção de joins e a seleção de atributos.
        val topicoRoot: Root<Topico> = criteriaQuery.from(Topico::class.java)

// Cria um join entre a entidade Topico e a entidade Curso. O join é realizado com base no relacionamento definido na entidade Topico.
// Isso permite que você acesse os atributos da entidade Curso a partir da entidade Topico.
        val cursoRoot: Join<Topico, Curso> = topicoRoot.join("curso")

// Configura a consulta para selecionar um novo objeto TopicoPorCategoriaDto.
// O TopicoPorCategoriaDto é construído usando dois parâmetros:
// - O valor da propriedade "categoria" da entidade Curso, obtido através de cursoRoot.get<String>("categoria").
// - A contagem de entidades Topico, obtida usando criteriaBuilder.count(topicoRoot).
// A consulta é agrupada por categoria, o que significa que o resultado será uma lista de categorias com o número de tópicos por categoria.
        criteriaQuery.select(
            criteriaBuilder.construct(
                TopicoPorCategoriaDto::class.java,
                cursoRoot.get<String>("categoria"),
                criteriaBuilder.count(topicoRoot)
            )
        ).groupBy(cursoRoot.get<String>("categoria"))

// Executa a consulta criada e retorna o resultado como uma lista. O método createQuery cria uma instância de Query que pode ser executada.
// A chamada a resultList executa a consulta e retorna os resultados em uma lista.
        return entityManager.createQuery(criteriaQuery).resultList
    }
}