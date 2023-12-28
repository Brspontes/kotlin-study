package com.brspontes.studyproject.controllers.response

import com.brspontes.studyproject.enums.BookStatus
import java.math.BigDecimal

data class BookResponse(
    val id: Int? = null,
    val name: String,
    val price: BigDecimal,
    val customer: CustomerResponse,
    val status: BookStatus? = null
)