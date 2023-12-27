package com.brspontes.studyproject.extensions

import com.brspontes.studyproject.controllers.requests.PostBookRequest
import com.brspontes.studyproject.controllers.requests.PostCustomerRequest
import com.brspontes.studyproject.controllers.requests.PutCustomerRequest
import com.brspontes.studyproject.enums.BookStatus
import com.brspontes.studyproject.models.BookModel
import com.brspontes.studyproject.models.CustomerDto

fun PostCustomerRequest.toCustomerModel() : CustomerDto {
    return CustomerDto(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: Int) : CustomerDto {
    return CustomerDto(id = id, name = this.name, email = this.email)
}

fun PostBookRequest.toBookmodel(customer: CustomerDto): BookModel {
    return BookModel(name = this.name, price = this.price, status = BookStatus.ATIVO, customer = customer)
}