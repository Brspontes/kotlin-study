package br.com.alura.forum.mapper

import br.com.alura.forum.controllers.dto.NovoTopicoRequest
import br.com.alura.forum.controllers.dto.TopicoResponse
import br.com.alura.forum.model.Topico
import br.com.alura.forum.services.CursoService
import br.com.alura.forum.services.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoMapper(private val cursoService: CursoService,
                   private val autorService: UsuarioService): Mapper<NovoTopicoRequest, Topico> {
    override fun map(t: NovoTopicoRequest): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso).get(),
            autor = autorService.buscarPorId(t.idAutor).get())
    }
}