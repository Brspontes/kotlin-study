package com.brspontes.studyproject.extensions

import com.brspontes.studyproject.controllers.requests.PostCustomerRequest
import com.brspontes.studyproject.controllers.requests.PutCustomerRequest
import com.brspontes.studyproject.models.CustomerDto

fun PostCustomerRequest.toCustomerModel() : CustomerDto {
    return CustomerDto(name = this.name, email = this.email)
}

fun PutCustomerRequest.toCustomerModel(id: String) : CustomerDto {
    return CustomerDto(id = id, name = this.name, email = this.email)
}