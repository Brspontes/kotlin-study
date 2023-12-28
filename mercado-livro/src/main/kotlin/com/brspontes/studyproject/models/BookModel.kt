package com.brspontes.studyproject.models

import com.brspontes.studyproject.enums.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String,

        @Column
        var price: BigDecimal,

        @ManyToOne
        @JoinColumn(name = "customer_id")
        var customer: CustomerDto? = null
) {
        @Column
        @Enumerated(EnumType.STRING)
        var status: BookStatus? = null
                set(value) {
                        //field valor atual
                        if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO) {
                                throw Exception("Não é possível alterar um livro ${field!!.name} .")
                        }
                        field = value
                }

        //sobreescrita de construtor
        constructor(
                id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerDto? = null,
                status: BookStatus?)
            :
            this(id, name, price, customer) {
                this.status = status
            }
}
