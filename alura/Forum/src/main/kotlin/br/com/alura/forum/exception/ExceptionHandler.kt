package br.com.alura.forum.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFound(excepetion: NotFoundException, request: HttpServletRequest) : ErrorResponse{
        return ErrorResponse(
            status = HttpStatus.NOT_FOUND.value(),
            error = HttpStatus.NOT_FOUND.name,
            message = excepetion.message!!,
            path = request.servletPath)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(excepetion: NotFoundException, request: HttpServletRequest) : ErrorResponse{
        return ErrorResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
            error = HttpStatus.INTERNAL_SERVER_ERROR.name,
            message = excepetion.message ?: "Ocorreu um erro interno no servidor",
            path = request.servletPath)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleBadRequest(excepetion: MethodArgumentNotValidException, request: HttpServletRequest) : ErrorResponse {
        val errorMessage = HashMap<String, String?>()
        excepetion.bindingResult.fieldErrors.forEach { error -> errorMessage[error.field] = error.defaultMessage }
        return ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = excepetion.message ?: "",
            path = request.servletPath)
    }
}