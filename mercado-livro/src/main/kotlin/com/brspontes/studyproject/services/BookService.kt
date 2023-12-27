package com.brspontes.studyproject.services

import com.brspontes.studyproject.models.BookModel
import com.brspontes.studyproject.repository.BookRepository

class BookService(val bookRepository: BookRepository) {
    fun create(bookModel: BookModel) {
        bookRepository.save(bookModel)
    }
}
