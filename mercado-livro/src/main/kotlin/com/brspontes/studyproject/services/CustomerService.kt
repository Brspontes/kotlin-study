package com.brspontes.studyproject.services

import com.brspontes.studyproject.controllers.requests.PostCustomerRequest
import com.brspontes.studyproject.controllers.requests.PutCustomerRequest
import com.brspontes.studyproject.models.CustomerDto
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import java.util.*

@Service
class CustomerService {
    val customers = mutableListOf<CustomerDto>()

    fun getAll(name: String?): List<CustomerDto> {
        name?.let {
            return customers.filter { it.name.contains(name, true) }
        }
        return customers
    }

    fun getCustomer(id: String): CustomerDto? {
        return customers.find { customerDto -> customerDto.id == id }
    }

    fun updateCustomer(customer: CustomerDto) {
        var customerUpdated = customers.find { customerDto -> customerDto.id == customer.id }.let {
            it?.name = customer.name
            it?.email= customer.email
        }
    }

    fun removeCustomer(id: String) {
        customers.removeIf { customerDto -> customerDto.id == id }
    }

    fun createCustomer(@RequestBody customer: CustomerDto) {
        CustomerDto("", customer.email, customer.name)
        customers.add(CustomerDto(UUID.randomUUID().toString(), customer.email, customer.name))
    }
}