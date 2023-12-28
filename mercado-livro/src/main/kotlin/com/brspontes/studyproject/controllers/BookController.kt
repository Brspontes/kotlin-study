package com.brspontes.studyproject.controllers

import com.brspontes.studyproject.controllers.requests.PostBookRequest
import com.brspontes.studyproject.controllers.requests.PutBookRequest
import com.brspontes.studyproject.controllers.response.BookResponse
import com.brspontes.studyproject.extensions.toBookResponse
import com.brspontes.studyproject.extensions.toBookmodel
import com.brspontes.studyproject.services.BookService
import com.brspontes.studyproject.services.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: PostBookRequest) {
        val customer = customerService.getCustomer(request.customerId)
        bookService.create(request.toBookmodel(customer!!))
    }

    @GetMapping
    fun findAll(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> {
        return bookService.findAll(pageable).map { it.toBookResponse() }
    }

    @GetMapping("/active")
    fun findActives(): List<BookResponse> {
        return bookService.findActives().map { it.toBookResponse() }
    }

    @GetMapping("/{id}")
    fun findById(id: Int): BookResponse {
        return bookService.findById(id).toBookResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(id: Int): Unit {
        return bookService.delete(id)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val boolSaved = bookService.findById(id)
        bookService.update(book.toBookmodel(boolSaved))
    }
}