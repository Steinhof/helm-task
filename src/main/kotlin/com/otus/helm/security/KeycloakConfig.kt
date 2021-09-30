package com.otus.helm.security

import org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class KeycloakConfig(
    @Value("\${keycloak.credentials.secret}")
    private val secretKey: String,
    @Value("\${keycloak.resource}")
    private val client: String,
    @Value("\${keycloak.auth-server-url}")
    private val authUrl: String,
    @Value("\${keycloak.realm}")
    private val realm: String
) {

    @Bean
    fun keycloak(): Keycloak {
        return KeycloakBuilder.builder()
            .grantType(CLIENT_CREDENTIALS)
            .serverUrl(authUrl)
            .realm(realm)
            .clientId(client)
            .clientSecret(secretKey)
            .build()
    }
}