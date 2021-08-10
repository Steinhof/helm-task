//package com.otus.helm.security
//
//import com.otus.helm.repository.UserRepository
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.stereotype.Service
//
//
//@Service
//class UserPrincipalService(
//    private val userRepository: UserRepository
//) : UserDetailsService {
//
//
//    @Throws(UsernameNotFoundException::class)
//    override fun loadUserByUsername(login: String): UserDetails {
//        return try {
//            val user = userRepository.findByLogin(login)
//            UserPrincipal()
//        } catch (exp: Exception) {
//            throw UsernameNotFoundException("not found")
//        }
//    }
//}