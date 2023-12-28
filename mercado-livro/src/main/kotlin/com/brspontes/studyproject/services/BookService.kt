package com.brspontes.studyproject.services

import com.brspontes.studyproject.enums.BookStatus
import com.brspontes.studyproject.models.BookModel
import com.brspontes.studyproject.models.CustomerDto
import com.brspontes.studyproject.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(val bookRepository: BookRepository) {
    fun create(bookModel: BookModel) {
        bookRepository.save(bookModel)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable);
    }

    fun findActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow { Exception("Não existe esse recurso") }
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO

        bookRepository.save(book)
    }

    fun update(book: BookModel): Unit {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerDto) {
        val books = bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}
