package br.com.alura.forum.services

import br.com.alura.forum.controllers.dto.AtualizacaoTopicoRequest
import br.com.alura.forum.controllers.dto.NovoTopicoRequest
import br.com.alura.forum.controllers.dto.TopicoPorCategoriaDto
import br.com.alura.forum.controllers.dto.TopicoResponse
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoMapper
import br.com.alura.forum.mapper.TopicoResponseMapper
import br.com.alura.forum.repositories.TopicoRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicoService(private val topicoRepository: TopicoRepository,
                    private val topicoResponseMapper: TopicoResponseMapper,
                    private val topicoMapper: TopicoMapper) {
    init {
//        val cursoKotlin = Curso(1, "kotlin", "desenvolvimento")
//        val autorAna = Usuario(1, "Ana", "teste@teste.com")
//
//        val topico1 = Topico(1, "Dúvida kotlin", "Teste", curso = cursoKotlin, autor = autorAna)
//        val topico2 = Topico(2, "Dúvida sobre listas", "Como usar listas em Kotlin?", curso = cursoKotlin, autor = autorAna)
//        val topico3 = Topico(3, "Dúvida sobre corrotinas", "Como funcionam as corrotinas?", curso = cursoKotlin, autor = autorAna)

//        topicos = Arrays.asList(topico1, topico2, topico3)
    }
    fun listar(nomeCurso: String?): List<TopicoResponse> {
        return if (nomeCurso != null)
            topicoRepository.findByCursoNome(nomeCurso)
            .toList().map { it -> topicoResponseMapper.map(it) }
        else
            topicoRepository.findAll()
                .toList().map { it -> topicoResponseMapper.map(it) }
    }

    fun buscarPorId(id: Long): TopicoResponse {
        val topico = topicoRepository.findById(id).orElseThrow {NotFoundException("Tópico não encontrado")}
        return topicoResponseMapper.map(topico)
    }

    fun cadastrar(topico: NovoTopicoRequest): TopicoResponse {
        val topico = topicoMapper.map(topico)
        return topicoResponseMapper.map(topicoRepository.save(topico))
    }

    fun atualizar(topico: AtualizacaoTopicoRequest): TopicoResponse {
        val topicoFounded = topicoRepository.findById(topico.id).orElseThrow {NotFoundException("Tópico não encontrado")}

        topicoFounded.titulo = topico.titulo
        topicoFounded.mensagem = topico.mensagem

        return topicoResponseMapper.map(topicoRepository.save(topicoFounded))
    }

    fun deletar(id: Long) {
        val topicoFounded = topicoRepository.findById(id).orElseThrow {NotFoundException("Tópico não encontrado")}
        topicoRepository.delete(topicoFounded)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return topicoRepository.relatorio()
    }
}