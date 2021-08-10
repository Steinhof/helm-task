package com.otus.helm.repository

import com.otus.helm.entity.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveCrudRepository<User, Long> {

    fun findByLogin(login: String): Mono<User>
}

