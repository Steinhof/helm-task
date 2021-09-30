package com.otus.helm.api.v1

import com.otus.helm.security.RoleService
import com.otus.helm.security.UserService
import org.keycloak.authorization.client.AuthzClient
import org.keycloak.authorization.client.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
class UserController(
    @Value("\${keycloak.credentials.secret}")
    private val secretKey: String,
    @Value("\${keycloak.resource}")
    private val client: String,
    @Value("\${keycloak.auth-server-url}")
    private val authUrl: String,
    @Value("\${keycloak.realm}")
    private val realm: String,
    private val userService: UserService,
    private val roleService: RoleService
) {
    @GetMapping
    fun findAll() =
        userService.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: String) =
        userService.findById(id)

    @GetMapping("/username/{username}")
    fun findByUsername(@PathVariable username: String) =
        userService.findByUsername(username)

    @PostMapping("/signup")
    fun create(@RequestBody userRequest: UserRequest): ResponseEntity<URI> {
        val response = userService.create(userRequest)

        if (response.status != 201)
            throw RuntimeException("User was not created")

        return ResponseEntity.created(response.location).build()
    }

    @PostMapping("/login")
    fun login(@RequestBody userRequest: UserLoginRequest): ResponseEntity<*>? {
        val clientCredentials: MutableMap<String, Any> = HashMap()

        clientCredentials["secret"] = secretKey
        clientCredentials["grant_type"] = "password"

        val configuration = Configuration(authUrl, realm, client, clientCredentials, null)
        val authzClient: AuthzClient = AuthzClient.create(configuration)
        val response = authzClient.obtainAccessToken(userRequest.username, userRequest.password)

        return ResponseEntity.ok(response)
    }


    @PostMapping("/{userId}/group/{groupId}")
    fun assignToGroup(
        @PathVariable userId: String,
        @PathVariable groupId: String
    ) {
        userService.assignToGroup(userId, groupId)
    }

    @PostMapping("/{userId}/role/{roleName}")
    fun assignRole(
        @PathVariable userId: String,
        @PathVariable roleName: String
    ) {
        val role = roleService.findByName(roleName)

        userService.assignRole(userId, role)
    }
}

