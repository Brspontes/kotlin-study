package com.brspontes.studyproject.excpetion

import com.brspontes.studyproject.controllers.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(400, "Este recurso não existe", internalCode = "001", errors = null)
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }
}