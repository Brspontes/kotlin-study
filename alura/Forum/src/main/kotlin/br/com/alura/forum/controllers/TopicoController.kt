package br.com.alura.forum.controllers

import br.com.alura.forum.controllers.dto.AtualizacaoTopicoRequest
import br.com.alura.forum.controllers.dto.NovoTopicoRequest
import br.com.alura.forum.controllers.dto.TopicoResponse
import br.com.alura.forum.services.TopicoService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topicos")
class TopicoController(private val service: TopicoService) {
    @GetMapping
    fun listar(): List<TopicoResponse> {
       return service.listar()
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