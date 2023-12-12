package Fundamentos

class Pessoa(var name: String, var age: Int) {
    override fun toString(): String {
        return "Pessoa é o $name com $age anos"
    }
}

fun main() {
    val pessoa = Pessoa("Brian", 28)
    println(pessoa.toString())
}