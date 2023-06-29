package com.example.kotlindemo.domain.entity

import jakarta.persistence.*


@Entity(name = "users")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        var id: String? = null,
        var name: String? = null,
        var email: String? = null,
        var age: Int? = null,
        var phone: String? = null
)