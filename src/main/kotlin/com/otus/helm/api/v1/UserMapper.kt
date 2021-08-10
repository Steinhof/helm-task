package com.otus.helm.api.v1

import com.otus.helm.entity.User
import com.otus.helm.entity.UserRole

object UserMapper {

    fun from(userCreateRequest: UserCreateRequest): User {
        return User(
            id = null,
            login = userCreateRequest.login,
            password = userCreateRequest.password,
            name = userCreateRequest.name,
            role = UserRole.USER.name
        )
    }

    fun from(userUpdate: UserUpdateRequest, userSource: User): User {
        return User(
            id = userSource.id,
            login = userUpdate.login ?: userSource.login,
            password = userUpdate.password ?: userSource.password,
            name = userUpdate.name ?: userSource.name,
            role = userUpdate.role ?: userSource.role
        )
    }
}