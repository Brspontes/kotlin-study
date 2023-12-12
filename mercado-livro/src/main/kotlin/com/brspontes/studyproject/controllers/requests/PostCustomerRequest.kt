package com.brspontes.studyproject.controllers.requests

import com.brspontes.studyproject.models.CustomerDto

data class PostCustomerRequest (
        var email: String,
        var name: String
)