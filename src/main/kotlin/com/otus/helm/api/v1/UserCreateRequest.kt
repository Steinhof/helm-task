package com.otus.helm.api.v1

data class UserCreateRequest(
    val login: String,
    val password: String,
    val name: String
)
