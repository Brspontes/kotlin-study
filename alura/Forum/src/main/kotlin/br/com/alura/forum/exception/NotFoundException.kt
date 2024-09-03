package br.com.alura.forum.exception

import java.util.function.Supplier

class NotFoundException(message: String) : RuntimeException(message) {
}