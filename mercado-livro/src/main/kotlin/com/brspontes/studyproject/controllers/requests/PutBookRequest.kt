package com.brspontes.studyproject.controllers.requests

import java.math.BigDecimal

data class PutBookRequest (
    var name: String?,
    var price: BigDecimal?
)