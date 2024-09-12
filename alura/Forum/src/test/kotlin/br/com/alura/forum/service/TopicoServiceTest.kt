package br.com.alura.forum.service

import br.com.alura.forum.mapper.TopicoMapper
import br.com.alura.forum.mapper.TopicoResponseMapper
import br.com.alura.forum.model.Curso
import br.com.alura.forum.model.Resposta
import br.com.alura.forum.model.Topico
import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repositories.TopicoRepository
import br.com.alura.forum.services.TopicoService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class TopicoServiceTest {
    val cursoMock = Curso(1, "Kotlin", "Desenvolvimento")
    val autorMock = Usuario(1, "Aluno Teste", "aluno@email.com","123456")
    val topicoRepository: TopicoRepository = mockk {
        every { findByCursoNome(any()) } returns listOf(
            Topico(
                id = 1,
                titulo = "",
                mensagem = "",
                curso = cursoMock,
                autor = autorMock,
                dataAlteracao = null
            )
        )
    }
    val topicoResponseMapper = TopicoResponseMapper()
    val topicoMapper: TopicoMapper = mockk()
    val topicoService = TopicoService(topicoRepository, topicoResponseMapper, topicoMapper)

    @Test
    fun `Deve listar topicos a partir do nome do curso` () {
        topicoService.listar("Kotlin")
        verify(exactly = 1) { topicoRepository.findByCursoNome(any()) }
    }
}