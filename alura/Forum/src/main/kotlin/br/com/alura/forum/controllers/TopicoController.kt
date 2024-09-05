package br.com.alura.forum.controllers

import br.com.alura.forum.controllers.dto.AtualizacaoTopicoRequest
import br.com.alura.forum.controllers.dto.NovoTopicoRequest
import br.com.alura.forum.controllers.dto.TopicoPorCategoriaDto
import br.com.alura.forum.controllers.dto.TopicoResponse
import br.com.alura.forum.services.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {
    @GetMapping
    fun listar(@RequestParam(required = false) nomeCurso: String?): List<TopicoResponse> {
       return service.listar(nomeCurso)
    }

    @GetMapping("/relatorio")
    fun relatorio(): List<TopicoPorCategoriaDto> {
        return service.relatorio()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoResponse {
        return service.buscarPorId(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    fun cadastrar(@RequestBody @Valid topico: NovoTopicoRequest,
                  uriBuider: UriComponentsBuilder): ResponseEntity<TopicoResponse> {
        val topicoResponse = service.cadastrar(topico)
        val uri = uriBuider.path("/topicos/${topicoResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(topicoResponse)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid topico: AtualizacaoTopicoRequest): TopicoResponse {
        return service.atualizar(topico)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}