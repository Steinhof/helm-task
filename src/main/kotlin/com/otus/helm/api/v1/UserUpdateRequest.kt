package com.otus.helm.api.v1

data class UserUpdateRequest(
    val id: Long,

    val login: String? = null,

    val password: String? = null,

    val name: String? = null,

    val role: String? = null
)