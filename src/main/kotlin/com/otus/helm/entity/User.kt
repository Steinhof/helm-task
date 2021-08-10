package com.otus.helm.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(

    @Id
    val id: Long?,

    val login: String,

    val password: String,

    val name: String,

    val role: String,
)
