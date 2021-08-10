package com.otus.helm.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

enum class UserRole {
    ADMIN,
    USER,
    GUEST
}

enum class Permissions {
    USER_UPDATE,
    USER_READ,
    USER_DELETE
}

@Table
data class Role(

    @Id
    private val name: UserRole,

    private val permissions: Permissions
)