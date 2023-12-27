package com.brspontes.studyproject.controllers

import com.brspontes.studyproject.controllers.requests.PostBookRequest
import com.brspontes.studyproject.extensions.toBookmodel
import com.brspontes.studyproject.services.BookService
import com.brspontes.studyproject.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

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
}