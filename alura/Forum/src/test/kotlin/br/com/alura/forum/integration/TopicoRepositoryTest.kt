package br.com.alura.forum.integration

import br.com.alura.forum.controllers.dto.TopicoPorCategoriaDto
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repositories.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

//@DataJpaTest
//@Testcontainers
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class TopicoRepositoryTest {
//    @Autowired
//    private lateinit var topicoRepository: TopicoRepository
//
//    val cursoMock = Curso(1, "Kotlin", "Desenvolvimento")
//    val autorMock = Usuario(1, "Aluno Teste", "aluno@email.com","123456")
//    private val topico =  Topico(
//        id = 1,
//        titulo = "",
//        mensagem = "",
//        curso = cursoMock,
//        autor = autorMock,
//        dataAlteracao = null
//    )
//
//    companion object {
//        @Container
//        private val mySQLContainer = MySQLContainer<Nothing>("mysql")
//            .apply {
//                withDatabaseName("testedb")
//                withUsername("brspontes")
//                withPassword("123456")
//            }
//
//        @JvmStatic
//        @DynamicPropertySource
//        fun properties(registry: DynamicPropertyRegistry) {
//            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
//            registry.add("spring.datasource.password", mySQLContainer::getPassword)
//            registry.add("spring.datasource.username", mySQLContainer::getUsername)
//        }
//    }
//
//    @Test
//    fun `Deve gerar um relatório`() {
//        topicoRepository.save(topico)
//        val relatorio = topicoRepository.relatorio()
//
//        assertThat(relatorio).isNotNull
//        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDto::class.java)
//    }
//}