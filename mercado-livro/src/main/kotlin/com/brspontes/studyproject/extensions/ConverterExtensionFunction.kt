package com.brspontes.studyproject.extensions

import com.brspontes.studyproject.controllers.requests.PostBookRequest
import com.brspontes.studyproject.controllers.requests.PostCustomerRequest
import com.brspontes.studyproject.controllers.requests.PutBookRequest
import com.brspontes.studyproject.controllers.requests.PutCustomerRequest
import com.brspontes.studyproject.controllers.response.BookResponse
import com.brspontes.studyproject.controllers.response.CustomerResponse
import com.brspontes.studyproject.enums.BookStatus
import com.brspontes.studyproject.enums.CustomerStatus
import com.brspontes.studyproject.models.BookModel
import com.brspontes.studyproject.models.CustomerDto

fun PostCustomerRequest.toCustomerModel() : CustomerDto {
    return CustomerDto(
        name = this.name,
        email = this.email, status =
        CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousCustomer: CustomerDto) : CustomerDto {
    return CustomerDto(
        id = previousCustomer.id,
        name = this.name,
        email = this.email,
        status = previousCustomer.status)
}

fun PostBookRequest.toBookmodel(customer: CustomerDto): BookModel {
    return BookModel(name = this.name, price = this.price, status = BookStatus.ATIVO, customer = customer)
}

fun PutBookRequest.toBookmodel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer)
}

fun CustomerDto.toCustomerResponse(): CustomerResponse {
    return CustomerResponse(this.name, this.status, this.email)
}

fun BookModel.toBookResponse(): BookResponse {
    return BookResponse(this.id, this.name, this.price,
        CustomerResponse(this.customer!!.name, this.customer!!.status, this.customer!!.email), this.status
    )
}