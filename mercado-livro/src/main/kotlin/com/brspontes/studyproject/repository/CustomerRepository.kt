package com.brspontes.studyproject.repository

import com.brspontes.studyproject.models.CustomerDto
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerDto, Int> {
    fun findByNameContaining(name: String): List<CustomerDto>
}