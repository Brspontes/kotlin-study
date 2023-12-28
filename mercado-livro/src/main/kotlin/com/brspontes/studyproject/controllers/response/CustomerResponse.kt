package com.brspontes.studyproject.controllers.response

import com.brspontes.studyproject.enums.CustomerStatus

data class CustomerResponse (
    val name: String,
    val status: CustomerStatus,
    val email: String
)
