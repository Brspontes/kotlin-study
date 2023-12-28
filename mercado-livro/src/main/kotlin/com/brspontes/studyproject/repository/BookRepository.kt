package com.brspontes.studyproject.repository

import com.brspontes.studyproject.enums.BookStatus
import com.brspontes.studyproject.models.BookModel
import com.brspontes.studyproject.models.CustomerDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {
    fun findByStatus(ativo: BookStatus): List<BookModel>
    fun findByCustomer(customer: CustomerDto): List<BookModel>

    fun findAll(pageable: Pageable): Page<BookModel>
}