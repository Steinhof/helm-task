package com.otus.helm.api.v1

import com.otus.helm.entity.User
import com.otus.helm.repository.UserRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/api/$apiVersion")
class UserResource(
    private val userRepository: UserRepository
) {

    @PostMapping("/user")
    fun createUser(@RequestBody userRequest: UserCreateRequest): Mono<User> {
        return userRepository.save(UserMapper.from(userRequest))
    }

    @GetMapping("/user/{login}")
    fun getUser(@PathVariable login: String): Mono<User> {
        return userRepository.findByLogin(login)
    }

    @PutMapping("/user/{login}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    fun updateUser(@RequestBody userRequest: UserUpdateRequest, @PathVariable login: String): Mono<User?> {
        return userRepository
            .findByLogin(login)
            .log()
            .map { user -> UserMapper.from(userRequest, user) }
            .flatMap { user -> userRepository.save(user) }
    }
}





