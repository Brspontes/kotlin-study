package br.com.alura.forum.repositories

import br.com.alura.forum.model.Topico
import org.springframework.data.repository.CrudRepository

interface TopicoRepository : CrudRepository<Topico, Long> {

}