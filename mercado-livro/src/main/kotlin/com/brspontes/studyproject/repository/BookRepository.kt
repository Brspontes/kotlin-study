package com.brspontes.studyproject.repository

import com.brspontes.studyproject.models.BookModel
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookModel, Int> {

}