package com.brspontes.studyproject.controllers

import com.brspontes.studyproject.controllers.requests.PostCustomerRequest
import com.brspontes.studyproject.controllers.requests.PutCustomerRequest
import com.brspontes.studyproject.extensions.toCustomerModel
import com.brspontes.studyproject.models.CustomerDto
import com.brspontes.studyproject.services.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("customer")
class CustomerController(val customerService: CustomerService) {

    @GetMapping
    fun getAll(@RequestParam name: String?): List<CustomerDto> {
        return customerService.getAll(name)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: Int): CustomerDto? {
        return customerService.getCustomer(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: String, @RequestBody customer: PutCustomerRequest) {
       customerService.updateCustomer(customer.toCustomerModel(id.toInt()))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: Int) {
        customerService.removeCustomer(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createCustomer(@RequestBody customer: PostCustomerRequest) {
      customerService.createCustomer(customer.toCustomerModel())
    }
}