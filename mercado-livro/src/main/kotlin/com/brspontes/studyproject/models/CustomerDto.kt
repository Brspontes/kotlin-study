package com.brspontes.studyproject.models

import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerDto(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var email: String,

        @Column
        var name: String
)
