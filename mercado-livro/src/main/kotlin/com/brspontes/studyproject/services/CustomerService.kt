package com.brspontes.studyproject.services

import com.brspontes.studyproject.controllers.requests.PostCustomerRequest
import com.brspontes.studyproject.controllers.requests.PutCustomerRequest
import com.brspontes.studyproject.enums.CustomerStatus
import com.brspontes.studyproject.models.CustomerDto
import com.brspontes.studyproject.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Service
class CustomerService(val customerRepository: CustomerRepository, val bookService: BookService) {
    val customers = mutableListOf<CustomerDto>()

    fun getAll(name: String?): List<CustomerDto> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun getCustomer(id: Int): CustomerDto? {
        return customerRepository.findById(id).orElseThrow()
    }

    fun updateCustomer(customer: CustomerDto) {
        if(!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun removeCustomer(id: Int) {
        val customer = getCustomer(id)
        bookService.deleteByCustomer(customer!!)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun createCustomer(@RequestBody customer: CustomerDto) {
        customerRepository.save(customer)
    }
}