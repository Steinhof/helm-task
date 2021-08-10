package com.otus.helm.security

import org.springframework.security.core.CredentialsContainer
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserPrincipal(
    private val username: String,
    private val password: String,
    private val authorities: MutableCollection<GrantedAuthority>
) : UserDetails, CredentialsContainer {

    override fun getUsername(): String {
        return username
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority> {
        return authorities;

    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun eraseCredentials() {
        return
    }
}