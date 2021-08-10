package com.otus.helm.api.v1

import com.otus.helm.entity.User
import com.otus.helm.repository.UserRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1")
class UserResource(private val userRepository: UserRepository) {

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: String): User {
        return userRepository.findById(id.toLong()).get()
    }

    @PostMapping("/user")
    fun createUser(@RequestBody user: User): User {
        return userRepository.save(user)
    }

    @DeleteMapping("/user/{name}")
    fun deleteUser(@PathVariable name: String) {
        userRepository.deleteById(name.toLong())
    }

    @PutMapping("/user/{id}")
    fun updateUser(@RequestBody user: User, @PathVariable id: String): User {
        val existing = userRepository.findById(id.toLong())
        if (existing.isEmpty) {
            return userRepository.save(user)
        }
        val updatedUser: User = existing.get()
        updatedUser.username = user.username
        updatedUser.firstname = user.firstname
        updatedUser.lastname = user.lastname
        updatedUser.email = user.email
        updatedUser.phone = user.phone
        return userRepository.save(updatedUser)
    }
}





