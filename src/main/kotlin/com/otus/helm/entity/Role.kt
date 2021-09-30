package com.otus.helm.entity

import org.springframework.data.annotation.Id
import javax.persistence.Entity

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

data class Role(

    @Id
    val name: UserRole,

    val permissions: List<String>
)