package com.otus.helm.security

import com.otus.helm.repository.RoleRepository
import com.otus.helm.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono


@Service
class UserPrincipalService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository
) : UserDetailsService {


    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(login: String): UserDetails {
        return try {
            userRepository
                .findByLogin(login)
                .map { user -> UserPrincipal(
                    username = user.login,
                    password = user.password,
                    authorities = roleRepository.findById(user.role)
                ) }
                .toFuture()
                .get()
        } catch (exp: Exception) {
            throw UsernameNotFoundException("not found")
        }
    }
}