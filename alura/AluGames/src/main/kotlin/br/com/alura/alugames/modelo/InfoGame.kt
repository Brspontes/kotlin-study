package org.example.br.com.alura.alugames.modelo

data class InfoGame(val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString()
    }
}